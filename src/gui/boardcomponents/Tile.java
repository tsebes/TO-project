package gui.boardcomponents;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    public static final int SIZE = 80;
    private Piece piece;

    public Tile() {
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    public void removePiece() {
        piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (piece != null) {
            piece.draw(g);
        }
    }
}
