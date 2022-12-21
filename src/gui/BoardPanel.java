package gui;

import game.*;
import game.enums.Player;
import gui.boardcomponents.Piece;
import gui.boardcomponents.PieceShapeFactory;
import gui.boardcomponents.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel implements BoardObserver {

    public static final int TILE_COUNT = Board.TILE_COUNT;

    private static final Color DARK = new Color(181, 136, 99);
    private static final Color LIGHT = new Color(240, 217, 181);

    private static final Color CAN_BE_CHOSEN = new Color(150, 150, 150);

    private final Tile[][] tiles = new Tile[TILE_COUNT][TILE_COUNT];

    private Coordinates lastClicked;

    private BoardGame game;
    private Board board;

    public BoardPanel() {
        setPreferredSize(new Dimension(Tile.SIZE * TILE_COUNT, Tile.SIZE * TILE_COUNT));
        setLayout(new GridLayout(TILE_COUNT, TILE_COUNT));
        addTiles();
    }

    public void updateBoard() {
        if (game != null) {
            game.removeBoardObserver(this);
        }
        game = RunGame.getInstance().getGame();
        game.addBoardObserver(this);
        board = game.getBoard();
        refreshTiles();
    }

    public void refreshTiles() {
        Field[][] fields = board.getFields();
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                tiles[i][j].setEnabled(true);
                if ((i + j) % 2 == 0) {
                    tiles[i][j].setBackground(LIGHT);
                } else {
                    tiles[i][j].setBackground(DARK);
                }
                tiles[i][j].setEnabled(false);
                if (fields[i][j].isEmpty()) {
                    tiles[i][j].removePiece();
                    tiles[i][j].setEnabled(false);
                } else if (fields[i][j].getPlayer() == Player.BLACK) {
                    tiles[i][j].setPiece(new Piece(PieceShapeFactory.getBlackMan()));
                    tiles[i][j].setEnabled(game.getCurrentTurn() == Player.BLACK);
                } else if (fields[i][j].getPlayer() == Player.WHITE) {
                    tiles[i][j].setPiece(new Piece(PieceShapeFactory.getWhiteMan()));
                    tiles[i][j].setEnabled(game.getCurrentTurn() == Player.WHITE);
                }
            }
        }
        if(board.isMultipleTake()){
            for (int i = 0; i < TILE_COUNT; i++) {
                for (int j = 0; j < TILE_COUNT; j++) {
                    tiles[i][j].setEnabled(false);
                }
            }
            tiles[board.getCurrent().x()][board.getCurrent().y()].setEnabled(true);
        }
        showPossibleMoves();
    }

    private void addTiles() {
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                Tile tile = new Tile();
                tile.setEnabled(false);
                Coordinates coordinates = new Coordinates(i, j);
                tile.addActionListener(e -> click(coordinates));
                add(tile);
                tiles[i][j] = tile;
            }
        }
    }

    private void click(Coordinates coordinates) {
        if (game.getPossibleMoves().contains(coordinates)) {
            game.move(lastClicked, coordinates);
        } else {
            game.updatePossibleMoves(coordinates);
            lastClicked = coordinates;
        }
    }

    private void showPossibleMoves() {
        for (Coordinates coordinates : game.getPossibleMoves()) {
            tiles[coordinates.x()][coordinates.y()].setBackground(CAN_BE_CHOSEN);
            tiles[coordinates.x()][coordinates.y()].setEnabled(true);
        }
    }

    @Override
    public void update() {
        refreshTiles();
    }
}
