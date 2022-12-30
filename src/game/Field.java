package game;

import game.enums.PieceType;
import game.enums.Player;

public class Field {

    private final Player player;
    private final PieceType pieceType;

    public Field() {
        this.player = null;
        this.pieceType = null;
    }

    public Field(Player player, PieceType pieceType) {
        this.player = player;
        this.pieceType = pieceType;
    }

    public boolean isEmpty() {
        return player == null || pieceType == null;
    }

    public Player getPlayer() {
        return player;
    }

    public PieceType getPiece() {
        return pieceType;
    }

}
