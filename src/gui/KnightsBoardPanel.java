package gui;

import game.BoardGame;
import game.Coordinates;
import gui.boardcomponents.Piece;
import gui.boardcomponents.PieceShapeFactory;
import gui.boardcomponents.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class KnightsBoardPanel extends JPanel implements BoardObserver{

    public static final int TILE_COUNT = 8;

    private static final Color DARK = new Color(181, 136, 99);
    private static final Color LIGHT = new Color(240, 217, 181);

    private static final Color CANBECHOSEN = new Color(150,150,150);

    private final List<Tile> tiles = new ArrayList<>(TILE_COUNT * TILE_COUNT);

    private BoardGame game;
    private String currentTurn;

    public KnightsBoardPanel() {
        setPreferredSize(new Dimension(Tile.SIZE * TILE_COUNT, Tile.SIZE * TILE_COUNT));
        setLayout(new GridLayout(TILE_COUNT, TILE_COUNT));
        addTiles();
        demoAddPieces();
        currentTurn = "WHITE";
    }

    public void setGame(BoardGame game) {
        this.game = game;
        this.game.addBoardObserver(this);
    }

    private void addTiles() {
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                Tile tile = new Tile();
                if ((i + j) % 2 == 0) {
                    tile.setBackground(LIGHT);
                } else {
                    tile.setBackground(DARK);
                }
                tile.setEnabled(false);
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
                        Coordinates coordinates = new Coordinates(j, i);
                        tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.show(coordinates, false));
                        tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    }
                }
            }
        }
    }

    private void endTurn(){
        if(currentTurn.equals("WHITE")){
            currentTurn = "BLACK";
            for (int i = 0; i < TILE_COUNT; i++) {
                for (int j = 0; j < TILE_COUNT; j++) {
                    if(tiles.get(i * TILE_COUNT + j).getPiece() != null && tiles.get(i * TILE_COUNT + j).getPiece().getShape().getColor() == Color.BLACK) {
                        Coordinates coordinates = new Coordinates(j, i);
                        tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.show(coordinates, false));
                        tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    }
                }
            }
        }else{
            currentTurn = "WHITE";
            for (int i = 0; i < TILE_COUNT; i++) {
                for (int j = 0; j < TILE_COUNT; j++) {
                    if(tiles.get(i * TILE_COUNT + j).getPiece() != null && tiles.get(i * TILE_COUNT + j).getPiece().getShape().getColor() == Color.WHITE) {
                        Coordinates coordinates = new Coordinates(j, i);
                        tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.show(coordinates, false));
                        tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    }
                }
            }
        }
    }

    private void addPossibleMoves(Coordinates piece, List<Coordinates> places){
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 == 1){
                    for (ActionListener listener : tiles.get(i * TILE_COUNT + j).getActionListeners()) {
                        tiles.get(i * TILE_COUNT + j).removeActionListener(listener);
                    }
                    tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    tiles.get(i * TILE_COUNT + j).setEnabled(false);
                    tiles.get(i * TILE_COUNT + j).setBackground(DARK);

                    if(currentTurn.equals("WHITE")){
                        if(tiles.get(i * TILE_COUNT + j).getPiece() != null && tiles.get(i * TILE_COUNT + j).getPiece().getShape().getColor() == Color.WHITE) {
                            Coordinates coordinates = new Coordinates(j, i);
                            tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.show(coordinates, false));
                            tiles.get(i * TILE_COUNT + j).setEnabled(true);
                        }
                    }else{
                        if(tiles.get(i * TILE_COUNT + j).getPiece() != null && tiles.get(i * TILE_COUNT + j).getPiece().getShape().getColor() == Color.BLACK) {
                            Coordinates coordinates = new Coordinates(j, i);
                            tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.show(coordinates, false));
                            tiles.get(i * TILE_COUNT + j).setEnabled(true);
                        }
                    }
                }

                for(Coordinates place : places){
                    if(place.getX() == j && place.getY() == i){
                        tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.move(piece, place));
                        tiles.get(i * TILE_COUNT + j).setEnabled(true);
                        tiles.get(i * TILE_COUNT + j).setBackground(CANBECHOSEN);
                    }
                }
            }
        }
    }

    private void addPossibleMovesContinue(Coordinates piece, List<Coordinates> places){
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 == 1) {
                    for (ActionListener listener : tiles.get(i * TILE_COUNT + j).getActionListeners()) {
                        tiles.get(i * TILE_COUNT + j).removeActionListener(listener);
                    }
                    tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    tiles.get(i * TILE_COUNT + j).setEnabled(false);
                    tiles.get(i * TILE_COUNT + j).setBackground(DARK);

                    for(Coordinates place : places) {
                        if (place.getX() == j && place.getY() == i) {
                            tiles.get(i * TILE_COUNT + j).addActionListener(e -> game.move(piece, place));
                            tiles.get(i * TILE_COUNT + j).setEnabled(true);
                            tiles.get(i * TILE_COUNT + j).setBackground(CANBECHOSEN);
                        }
                    }
                }
            }
        }
    }

    private void movePiece(Coordinates piece, List<Coordinates> places){
        if(tiles.get(places.get(0).getY() * TILE_COUNT + places.get(0).getX()).getPiece() != null){
            tiles.get(places.get(0).getY() * TILE_COUNT + places.get(0).getX()).removePiece();
        }
        tiles.get(places.get(0).getY() * TILE_COUNT + places.get(0).getX()).setPiece(tiles.get(piece.getY() * TILE_COUNT + piece.getX()).getPiece());
        tiles.get(piece.getY() * TILE_COUNT + piece.getX()).removePiece();
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 == 1) {
                    for (ActionListener listener : tiles.get(i * TILE_COUNT + j).getActionListeners()) {
                        tiles.get(i * TILE_COUNT + j).removeActionListener(listener);
                    }
                    tiles.get(i * TILE_COUNT + j).setEnabled(true);
                    tiles.get(i * TILE_COUNT + j).setEnabled(false);
                    tiles.get(i * TILE_COUNT + j).setBackground(DARK);
                }
            }
        }
        this.endTurn();
    }

    private void movePieceContinue(Coordinates piece, List<Coordinates> places) {
        tiles.get(places.get(0).getY() * TILE_COUNT + places.get(0).getX()).setPiece(tiles.get(piece.getY() * TILE_COUNT + piece.getX()).getPiece());
        tiles.get(piece.getY() * TILE_COUNT + piece.getX()).removePiece();

        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 == 1) {
                    if (i != places.get(0).getY() && j != places.get(0).getX()) {
                        for (ActionListener listener : tiles.get(i * TILE_COUNT + j).getActionListeners()) {
                            tiles.get(i * TILE_COUNT + j).removeActionListener(listener);
                        }
                        tiles.get(i * TILE_COUNT + j).setEnabled(true);
                        tiles.get(i * TILE_COUNT + j).setEnabled(false);
                    }
                    tiles.get(i * TILE_COUNT + j).setBackground(DARK);
                }
            }
        }
        game.show(places.get(0), true);
    }


    @Override
    public void boardUpdate(String action, Coordinates piece, List<Coordinates> places) {
        switch (action){
            case "ShowPossibilites":
                this.addPossibleMoves(piece, places);
                break;
            case "ShowPossibilitesContinue":
                this.addPossibleMovesContinue(piece, places);
                break;
            case "Move":
                this.movePiece(piece, places);
                break;
            case "MoveCanContinue":
                this.movePieceContinue(piece, places);
                break;
        }
    }
}
