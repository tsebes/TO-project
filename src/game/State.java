package game;

import game.enums.PieceEnum;

public abstract class State {
    protected PieceEnum pieceEnum;

    public State(PieceEnum pieceEnum) {
        this.pieceEnum = pieceEnum;
    }

    public abstract void becomeKing();


}
