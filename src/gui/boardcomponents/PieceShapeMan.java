package gui.boardcomponents;

import java.awt.*;

public class PieceShapeMan implements Shape {

    private static final int MARGIN = 5;
    private static final int SIZE = Tile.SIZE - 2 * MARGIN;
    private static final int BORDER_WIDTH = 4;

    private final Color color;

    public PieceShapeMan(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(MARGIN, MARGIN, SIZE, SIZE);
        g.setColor(color);
        g.fillOval(BORDER_WIDTH + MARGIN, BORDER_WIDTH + MARGIN,
                SIZE - BORDER_WIDTH * 2, SIZE - BORDER_WIDTH * 2);
    }
}
