package gui.boardcomponents;

import game.Piece;
import game.enums.PieceType;
import game.enums.Player;

import java.awt.*;

public class PieceShapeFactory {

    private static final Shape whiteMan = new PieceShapeMan(Color.WHITE);
    private static final Shape blackMan = new PieceShapeMan(Color.BLACK);
    private static final Shape whiteKing = new PieceShapeKing(Color.WHITE);
    private static final Shape blackKing = new PieceShapeKing(Color.BLACK);

    public static Shape getShapeFromPiece(Piece piece) {
        if (piece.getPlayer() == Player.BLACK && piece.getPieceType() == PieceType.MAN) {
            return blackMan;
        } else if (piece.getPlayer() == Player.WHITE && piece.getPieceType() == PieceType.MAN) {
            return whiteMan;
        } else if (piece.getPlayer() == Player.BLACK && piece.getPieceType() == PieceType.KING) {
            return blackKing;
        } else if (piece.getPlayer() == Player.WHITE && piece.getPieceType() == PieceType.KING) {
            return whiteKing;
        }
        return null;
    }
}
