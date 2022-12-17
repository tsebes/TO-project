package gui.boardcomponents;

import java.awt.*;

public class PieceShapeFactory {

    private static final Shape whiteMan = new PieceShapeMan(Color.WHITE);
    private static final Shape blackMan = new PieceShapeMan(Color.BLACK);
    private static final Shape whiteKing = new PieceShapeKing(Color.WHITE);
    private static final Shape blackKing = new PieceShapeKing(Color.BLACK);

    public static Shape getWhiteMan() {
        return whiteMan;
    }

    public static Shape getBlackMan() {
        return blackMan;
    }

    public static Shape getWhiteKing() {
        return whiteKing;
    }

    public static Shape getBlackKing() {
        return blackKing;
    }
}
