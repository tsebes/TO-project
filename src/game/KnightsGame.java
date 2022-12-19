package game;



import game.Knights.*;
import gui.BoardObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class KnightsGame implements BoardGame {

    private final Set<BoardObserver> observers = new HashSet<>();

    private Stack<String> history;
    private KShowPossibilities kShowPossibilitiesCommand;
    private KMove kMoveCommand;
    private KUndo kUndoCommand;
    private KRedo kRedoCommand;
    private KnightsBoard knightsBoard;

    @Override
    public void start() {
        System.out.println("Knights game - start");
        history = new Stack<String>();
        knightsBoard = new KnightsBoard();
        kShowPossibilitiesCommand = new KShowPossibilities(history, knightsBoard);
        kMoveCommand = new KMove(history, knightsBoard);
        kUndoCommand = new KUndo(history, knightsBoard);
        kRedoCommand = new KRedo(history, knightsBoard);
    }

    @Override
    public void show(Coordinates piece, boolean multipleTake){
        kShowPossibilitiesCommand.setShow(piece, multipleTake);
        kShowPossibilitiesCommand.execute();
        if(multipleTake){
            this.notifyBoardObservers("ShowPossibilitesContinue", piece, kShowPossibilitiesCommand.getPlaces());
        }else{
            this.notifyBoardObservers("ShowPossibilites", piece, kShowPossibilitiesCommand.getPlaces());
        }
    }

    @Override
    public void move(Coordinates start, Coordinates end){
        kMoveCommand.setMove(start, end);
        kMoveCommand.execute();
        if(kMoveCommand.isMultipleTake()){
            this.notifyBoardObservers("MoveCanContinue", start, kMoveCommand.getPlaces());
        }else{
            this.notifyBoardObservers("Move", start, kMoveCommand.getPlaces());
        }
    }

    @Override
    public void addBoardObserver(BoardObserver boardObserver) {
        observers.add(boardObserver);
    }

    @Override
    public void removeBoardObserver(BoardObserver boardObserver) {
        observers.remove(boardObserver);
    }

    @Override
    public void notifyBoardObservers(String action, Coordinates piece, List<Coordinates> places) {
        for(BoardObserver observer : observers){
            observer.boardUpdate(action, piece, places);
        }
    }
}
