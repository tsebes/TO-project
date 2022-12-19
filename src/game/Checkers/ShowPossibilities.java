package game.Checkers;

import game.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ShowPossibilities extends Command{
    private Coordinates piece = new Coordinates();
    private List<Coordinates> places = new ArrayList<>();
    private boolean mulitpleTake;


    public ShowPossibilities(Stack<String> history, CheckersBoard checkersBoard) {
        super(history, checkersBoard);
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
        places = checkersBoard.possibleMoves(piece, mulitpleTake);
    }

    @Override
    public void updateHistory() {
        //does not update history
    }


}
