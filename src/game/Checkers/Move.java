package game.Checkers;

import game.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Move extends Command{
    boolean multipleTake;
    private Coordinates piece = new Coordinates();
    private Coordinates end = new Coordinates();
    private List<Coordinates> places = new ArrayList<>();

    public Move(Stack<String> history, CheckersBoard checkersBoard) {
        super(history, checkersBoard);
    }

    public void setMove(Coordinates piece, Coordinates end){
        this.piece = piece;
        this.end = end;
    }

    public List<Coordinates> getPlaces(){
        return places;
    }

    public boolean isMultipleTake() {
        return multipleTake;
    }

    @Override
    public void execute() {
        places = checkersBoard.commitMove(piece, end);
        multipleTake = checkersBoard.isMultipleTake();
        if(!multipleTake){
            checkersBoard.endTurn();
        }
    }

    @Override
    public void updateHistory() {
        //TODO
    }

}
