package game;

import game.enums.Piece;
import game.enums.Player;

public class Field {

    private final Player player;
    private final Piece piece;

    public Field() {
        this.player = null;
        this.piece = null;
    }

    public Field(Player player, Piece piece) {
        this.player = player;
        this.piece = piece;
    }

    public boolean isEmpty() {
        return player == null || piece == null;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece getPiece() {
        return piece;
    }
}
