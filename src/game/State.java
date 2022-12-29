package game;

import game.enums.PieceType;

public abstract class State {
    protected PieceType pieceType;

    public State(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public abstract void becomeKing();

}
