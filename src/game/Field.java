package game;

import game.enums.PieceType;
import game.enums.Player;

public class Field {

    private Piece piece;

    public Field() {
    }

    public Field(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        piece = null;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Player getPlayer() {
        return piece == null ? null : piece.getPlayer();
    }

    public PieceType getPieceType() {
        return piece == null ? null : piece.getPieceType();
    }

}
