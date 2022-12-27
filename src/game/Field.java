package game;

import game.enums.PieceEnum;
import game.enums.Player;

public class Field {

    private final Player player;
    private final PieceEnum pieceEnum;

    public Field() {
        this.player = null;
        this.pieceEnum = null;
    }

    public Field(Player player, PieceEnum pieceEnum) {
        this.player = player;
        this.pieceEnum = pieceEnum;
    }

    public boolean isEmpty() {
        return player == null || pieceEnum == null;
    }

    public Player getPlayer() {
        return player;
    }

    public PieceEnum getPiece() {
        return pieceEnum;
    }

}
