package LLDTicTacToe.Strategy;

import LLDTicTacToe.Model.Board;
import LLDTicTacToe.Model.PieceType;

public class ColumnWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, int row, int column, PieceType pieceType) {
        for(int i = 0; i < board.size; i++) {
            if(board.board[i][column] == null || board.board[i][column].pieceType != pieceType) {
                return false;
            }
        }
        return true;
    }
} 