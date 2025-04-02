package LLDTicTacToe.Strategy;

import LLDTicTacToe.Model.Board;
import LLDTicTacToe.Model.PieceType;

public class RowWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, int row, int column, PieceType pieceType) {
        for(int i = 0; i < board.size; i++) {
            if(board.board[row][i] == null || board.board[row][i].pieceType != pieceType) {
                return false;
            }
        }
        return true;
    }
} 