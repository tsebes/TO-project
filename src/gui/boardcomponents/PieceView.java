package gui.boardcomponents;

import java.awt.*;

public class PieceView {

    private final Shape shape;

    public PieceView(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }
}
