package game;

import game.enums.Piece;
import game.enums.Player;

public abstract class State {
    protected Piece piece;

    public State(Piece piece) {
        this.piece = piece;
    }

    public abstract void becomeKing();


}
