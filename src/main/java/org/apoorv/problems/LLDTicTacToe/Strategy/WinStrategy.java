package LLDTicTacToe.Strategy;

import LLDTicTacToe.Model.Board;
import LLDTicTacToe.Model.PieceType;

public interface WinStrategy {
    boolean checkWin(Board board, int row, int column, PieceType pieceType);
} 