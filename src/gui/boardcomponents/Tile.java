package gui.boardcomponents;

import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {

    public static final int SIZE = 80;
    private PieceView pieceView;

    public Tile() {
        setPreferredSize(new Dimension(SIZE, SIZE));
    }

    public void removePiece() {
        pieceView = null;
    }

    public void setPiece(PieceView pieceView) {
        this.pieceView = pieceView;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pieceView != null) {
            pieceView.draw(g);
        }
    }
}
