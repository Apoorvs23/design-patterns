package LLDTicTacToe.Factory;

import LLDTicTacToe.Model.Player;
import LLDTicTacToe.Model.PieceType;
import LLDTicTacToe.Model.PlayingPiece;
import LLDTicTacToe.Model.PlayingPieceX;
import LLDTicTacToe.Model.PlayingPieceO;

public class PlayerFactory {
    public static Player createPlayer(String name, PieceType pieceType) {
        PlayingPiece piece = createPiece(pieceType);
        return new Player(name, piece);
    }

    private static PlayingPiece createPiece(PieceType pieceType) {
        return pieceType == PieceType.X ? new PlayingPieceX() : new PlayingPieceO();
    }
} 