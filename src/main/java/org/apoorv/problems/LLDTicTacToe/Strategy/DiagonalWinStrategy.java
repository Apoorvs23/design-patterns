package LLDTicTacToe.Strategy;

import LLDTicTacToe.Model.Board;
import LLDTicTacToe.Model.PieceType;

public class DiagonalWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, int row, int column, PieceType pieceType) {
        // Check main diagonal
        if (row == column) {
            for(int i = 0, j = 0; i < board.size; i++, j++) {
                if(board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
} 