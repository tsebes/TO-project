package game.Knights;

import game.Coordinates;
import game.Field;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.*;
import static game.Constants.white;
import static java.lang.Math.abs;

public class KnightsBoard {
    private Field[][] field = new Field[TILE_COUNT][TILE_COUNT];
    private String currentTurn;
    private boolean multipleTake;

    //Konstruktor

    public KnightsBoard(){
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 2) {
                        field[i][j] = new Field(black, man);
                    } else if (i > 5) {
                        field[i][j] = new Field(white, man);
                    } else{
                        field[i][j] = new Field();
                    }
                }
            }
        }
        this.currentTurn = white;
    }

    //Zmiana aktualnego gracza

    void endTurn(){
        if(currentTurn == white){
            currentTurn = black;
        }else{
            currentTurn = white;
        }
    }

//Getters

    public boolean isMultipleTake() {
        return multipleTake;
    }

    //Sprawdzanie mozliwosci ruchu normalnego pionka

    public boolean canMove(Coordinates piece){
        return (canBasicMove(piece)|| canJump(piece));
    }

    public boolean canBasicMove(Coordinates piece){
        return (canBasicMoveLeft(piece)||canBasicMoveRight(piece));
    }

    public boolean canBasicMoveLeft(Coordinates piece){
        int changeY;
        if(currentTurn == white){
            changeY = -1;
        }else{
            changeY = 1;
        }
        if(piece.getX() > 0 && piece.getY() + changeY < 8 && piece.getY() + changeY >= 0){
            if(field[piece.getY() + changeY][piece.getX() - 1].getPlayer() == null) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean canBasicMoveRight(Coordinates piece){
        int changeY;
        if(currentTurn == white){
            changeY = -1;
        }else{
            changeY = 1;
        }
        if(piece.getX() < 7 && piece.getY() + changeY < 8 && piece.getY() + changeY >= 0){
            if(field[piece.getY() + changeY][piece.getX() + 1].getPlayer() == null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean canJump(Coordinates piece){
        return (canJumpLeft(piece)|| canJumpRight(piece));
    }

    public boolean canJumpLeft(Coordinates piece){
        int changeY;
        String enemy;
        if(currentTurn == white){
            changeY = -1;
            enemy = black;
        }else{
            changeY = 1;
            enemy = white;
        }
        if(piece.getX() > 1 && piece.getY() + changeY < 7 && piece.getY() + changeY >= 1){
            if(field[piece.getY() + changeY][piece.getX() - 1].getPlayer() != null && field[piece.getY() + 2 * changeY][piece.getX() - 2].getPlayer() == null) {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean canJumpRight(Coordinates piece){
        int changeY;
        String enemy;
        if(currentTurn == white){
            changeY = -1;
            enemy = black;
        }else{
            changeY = 1;
            enemy = white;
        }
        if(piece.getX() < 6 && piece.getY() + changeY < 7 && piece.getY() + changeY >= 1){
            if(field[piece.getY() + changeY][piece.getX() + 1].getPlayer() != null && field[piece.getY() + 2 * changeY][piece.getX() + 2].getPlayer() == null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public List<Coordinates> possibleMoves(Coordinates piece, boolean multipleTake){
        List<Coordinates> places = new ArrayList<>();
        int changeY;

        if(currentTurn == white){
            changeY = -1;
        }else{
            changeY = 1;
        }
        if(!multipleTake && this.canBasicMoveLeft(piece)){
            places.add(new Coordinates(piece.getX() - 1, piece.getY() + changeY));
        }
        if(!multipleTake && this.canBasicMoveRight(piece)){
            places.add(new Coordinates(piece.getX() + 1, piece.getY() + changeY));
        }
        if(this.canJumpLeft(piece)){
            places.add(new Coordinates(piece.getX() - 2, piece.getY() + 2 * changeY));
        }
        if(this.canJumpRight(piece)){
            places.add(new Coordinates(piece.getX() + 2, piece.getY() + 2 * changeY));
        }

        return places;
    }

    public List<Coordinates> commitMove(Coordinates start, Coordinates end){
        List<Coordinates> places = new ArrayList<>();
        places.add(end);

        field[end.getY()][end.getX()].setNewValues(field[start.getY()][start.getX()].getPlayer(), field[start.getY()][start.getX()].getPiece());
        field[start.getY()][start.getX()].setNewValues();

        if(abs(start.getX() - end.getX()) > 1 && this.canJump(end)){
            multipleTake = true;
        }else{
            multipleTake = false;
        }

        return places;
    }

}
