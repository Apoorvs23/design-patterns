package LLDTicTacToe.Model;

import LLDTicTacToe.Strategy.*;
import java.util.ArrayList;
import java.util.List;

public class WinConditionChecker {
    private final Board board;
    private final List<WinStrategy> winStrategies;

    public WinConditionChecker(Board board) {
        this.board = board;
        this.winStrategies = new ArrayList<>();
        initializeStrategies();
    }

    private void initializeStrategies() {
        winStrategies.add(new RowWinStrategy());
        winStrategies.add(new ColumnWinStrategy());
        winStrategies.add(new DiagonalWinStrategy());
        winStrategies.add(new AntiDiagonalWinStrategy());
    }

    public boolean checkWinner(int row, int column, PieceType pieceType) {
        return winStrategies.stream()
                .anyMatch(strategy -> strategy.checkWin(board, row, column, pieceType));
    }
} 