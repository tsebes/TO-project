package gui.boardcomponents;

import java.awt.*;

public class PieceShapeKing implements Shape {

    private final Color color;
    private final Polygon crown;
    private final Polygon crownBorder;

    public PieceShapeKing(Color color) {
        this.color = color;
        this.crown = createCrown();
        this.crownBorder = createCrownBorder();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillPolygon(crownBorder);
        g.setColor(color);
        g.fillPolygon(crown);
    }

    private Polygon createCrown() {
        int[] xPoly = {8, 19, 61, 71, 56, 40, 24};
        int[] yPoly = {18, 66, 66, 18, 41, 16, 41};

        return new Polygon(xPoly, yPoly, xPoly.length);
    }

    private Polygon createCrownBorder() {
        int[] xPoly = {5, 15, 65, 75, 56, 40, 24};
        int[] yPoly = {10, 70, 70, 10, 35, 10, 35};

        return new Polygon(xPoly, yPoly, xPoly.length);
    }
}

