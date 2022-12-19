package game.Knights;

import game.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KShowPossibilities extends KCommand {
    private Coordinates piece = new Coordinates();
    private List<Coordinates> places = new ArrayList<>();
    private boolean mulitpleTake;


    public KShowPossibilities(Stack<String> history, KnightsBoard knightsBoard) {
        super(history, knightsBoard);
    }

    public void setShow(Coordinates piece, boolean multipleTake){
        this.piece = piece;
        this.mulitpleTake = multipleTake;
    }

    public List<Coordinates> getPlaces(){
        return places;
    }

    public boolean isMulitpleTake() {
        return mulitpleTake;
    }

    @Override
    public void execute() {
        places = knightsBoard.possibleMoves(piece, mulitpleTake);
    }

    @Override
    public void updateHistory() {
        //does not update history
    }


}