package LLDTicTacToe;

import LLDTicTacToe.Model.*;
import LLDTicTacToe.Factory.PlayerFactory;

import java.util.List;
import java.util.Scanner;

public class TicTacToeGame implements GameInterface {
    private final PlayerManager playerManager;
    private final Board gameBoard;
    private final WinConditionChecker winConditionChecker;
    private boolean gameOver;

    public TicTacToeGame() {
        this.playerManager = new PlayerManager();
        this.gameBoard = new Board(3);
        this.winConditionChecker = new WinConditionChecker(gameBoard);
        this.gameOver = false;
    }

    @Override
    public void initializeGame() {
        // Create players using factory
        Player player1 = PlayerFactory.createPlayer("Player1", PieceType.X);
        Player player2 = PlayerFactory.createPlayer("Player2", PieceType.O);

        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);
    }

    @Override
    public String startGame() {
        while (!gameOver) {
            Player currentPlayer = playerManager.getCurrentPlayer();
            
            // Display board and get free spaces
            gameBoard.printBoard();
            List<Pair<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
            if (freeSpaces.isEmpty()) {
                gameOver = true;
                return "tie";
            }

            // Get player move
            Move move = getPlayerMove(currentPlayer);
            if (move == null) continue;

            // Try to make the move
            if (!makeMove(move, currentPlayer)) {
                continue;
            }

            // Check for winner
            if (winConditionChecker.checkWinner(move.row, move.column, currentPlayer.playingPiece.pieceType)) {
                gameOver = true;
                return currentPlayer.name;
            }

            playerManager.nextPlayer();
        }
        return "tie";
    }

    private Move getPlayerMove(Player player) {
        try {
            System.out.print("Player:" + player.name + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();
            String[] values = input.split(",");
            return new Move(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        } catch (Exception e) {
            System.out.println("Invalid input format. Please enter row,column (e.g., 1,2)");
            return null;
        }
    }

    private boolean makeMove(Move move, Player player) {
        boolean pieceAddedSuccessfully = gameBoard.addPiece(move.row, move.column, player.playingPiece);
        if (!pieceAddedSuccessfully) {
            System.out.println("Incorrect position chosen, try again");
            playerManager.keepCurrentPlayer();
            return false;
        }
        return true;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Player getCurrentPlayer() {
        return playerManager.getCurrentPlayer();
    }

    private static class Move {
        final int row;
        final int column;

        Move(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
