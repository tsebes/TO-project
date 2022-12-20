package gui.boardcomponents;

import java.awt.*;

public class Piece {

    private final Shape shape;

    public Piece(Shape shape) {
        this.shape = shape;
    }

    public void draw(Graphics g) {
        shape.draw(g);
    }
}
