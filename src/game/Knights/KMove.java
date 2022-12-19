package game.Knights;

import game.Coordinates;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KMove extends KCommand {
    boolean multipleTake;
    private Coordinates piece = new Coordinates();
    private Coordinates end = new Coordinates();
    private List<Coordinates> places = new ArrayList<>();

    public KMove(Stack<String> history, KnightsBoard knightsBoard) {
        super(history, knightsBoard);
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
        places = knightsBoard.commitMove(piece, end);
        multipleTake = knightsBoard.isMultipleTake();
        if(!multipleTake){
            knightsBoard.endTurn();
        }
    }

    @Override
    public void updateHistory() {
        //TODO
    }

}
