package gui;

import gui.boardcomponents.Piece;
import gui.boardcomponents.PieceShapeFactory;
import gui.boardcomponents.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    public static final int TILE_COUNT = 8;

    private static final Color DARK = new Color(181, 136, 99);
    private static final Color LIGHT = new Color(240, 217, 181);

    private final List<Tile> tiles = new ArrayList<>(TILE_COUNT * TILE_COUNT);

    public BoardPanel() {
        setPreferredSize(new Dimension(Tile.SIZE * TILE_COUNT, Tile.SIZE * TILE_COUNT));
        setLayout(new GridLayout(TILE_COUNT, TILE_COUNT));
        addTiles();
        demoAddPieces();
    }

    private void addTiles() {
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                Tile tile = new Tile();
                tile.addActionListener(e -> tile.removePiece());
                if ((i + j) % 2 == 0) {
                    tile.setBackground(LIGHT);
                    tile.setEnabled(false);
                } else {
                    tile.setBackground(DARK);
                }
                add(tile);
                tiles.add(tile);
            }
        }
    }

    private void demoAddPieces() {
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        tiles.get(i * TILE_COUNT + j).setPiece(new Piece(PieceShapeFactory.getBlackMan()));
                    } else if (i > 4) {
                        tiles.get(i * TILE_COUNT + j).setPiece(new Piece(PieceShapeFactory.getWhiteMan()));
                    }
                }
            }
        }
    }
}