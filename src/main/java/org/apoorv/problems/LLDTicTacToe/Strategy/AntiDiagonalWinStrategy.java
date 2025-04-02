package LLDTicTacToe.Strategy;

import LLDTicTacToe.Model.Board;
import LLDTicTacToe.Model.PieceType;

public class AntiDiagonalWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, int row, int column, PieceType pieceType) {
        // Check anti-diagonal
        if (row + column == board.size - 1) {
            for(int i = 0, j = board.size-1; i < board.size; i++, j--) {
                if(board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
} 