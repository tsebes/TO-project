package game.Checkers;

import game.Coordinates;
import game.Field;

import java.util.ArrayList;
import java.util.List;

import static game.Constants.*;


public class CheckersBoard{
    private Field[][] field = new Field[TILE_COUNT][TILE_COUNT];
    private String currentTurn;
    private boolean multipleTake;

    //Konstruktor

    public CheckersBoard(){
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if ((i + j) % 2 != 0) {
                    if (i < 3) {
                        field[i][j] = new Field(black, man);
                    } else if (i > 4) {
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

    public boolean isMultipleTake() {
        return multipleTake;
    }

    //Sprawdzanie mozliwosci ruchu normalnego pionka

    public boolean canMove(Coordinates piece){
        return (canBasicMove(piece)||canTake(piece));
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

    public boolean canTake(Coordinates piece){
        return (canTakeLeft(piece)||canTakeRight(piece));
    }

    public boolean canTakeLeft(Coordinates piece){
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
                if(field[piece.getY() + changeY][piece.getX() - 1].getPlayer() == enemy && field[piece.getY() + 2 * changeY][piece.getX() - 2].getPlayer() == null) {
                    return true;
                }else{
                    return false;
                }
        }else{
            return false;
        }
    }

    public boolean canTakeRight(Coordinates piece){
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
            if(field[piece.getY() + changeY][piece.getX() + 1].getPlayer() == enemy && field[piece.getY() + 2 * changeY][piece.getX() + 2].getPlayer() == null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    //sprawdzanie listy mozliwych miejsc do poruszenia
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
        if(this.canTakeLeft(piece)){
            places.add(new Coordinates(piece.getX() - 2, piece.getY() + 2 * changeY));
        }
        if(this.canTakeRight(piece)){
            places.add(new Coordinates(piece.getX() + 2, piece.getY() + 2 * changeY));
        }

        return places;
    }

    //wykonanywanie ruchu

    public List<Coordinates> commitMove(Coordinates start, Coordinates end){
        List<Coordinates> places = new ArrayList<>();
        places.add(end);

        boolean movingToLeft = end.getX() < start.getX();
        boolean movingUp = end.getY() < start.getY();
        int i = start.getY(), j = start.getX();

        do{
            if(movingToLeft){
                j -= 1;
            }else{
                j += 1;
            }
            if(movingUp){
                i -= 1;
            }else{
                i += 1;
            }
            if(field[i][j].getPlayer() != null && field[i][j].getPlayer() != currentTurn){
                places.add(new Coordinates(j, i));
                field[i][j].setNewValues();
            }
        }while(i != end.getY() && j != end.getX());

        field[end.getY()][end.getX()].setNewValues(field[start.getY()][start.getX()].getPlayer(), field[start.getY()][start.getX()].getPiece());
        field[start.getY()][start.getX()].setNewValues();

        if(places.size() > 1 && this.canTake(end)){
            multipleTake = true;
        }else{
            multipleTake = false;
        }

        return places;
    }
}
