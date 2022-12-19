package game;

import game.Checkers.*;
import gui.BoardObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class CheckersGame implements BoardGame {

    private final Set<BoardObserver> observers = new HashSet<>();

    private Stack<String> history;
    private ShowPossibilities CShowPossibilitiesCommand;
    private Move CMoveCommand;
    private Undo CUndoCommand;
    private Redo CRedoCommand;
    private CheckersBoard checkersBoard;

    @Override
    public void start() {
        System.out.println("Checkers game - start");
        history = new Stack<String>();
        checkersBoard = new CheckersBoard();
        CShowPossibilitiesCommand = new ShowPossibilities(history, checkersBoard);
        CMoveCommand = new Move(history, checkersBoard);
        CUndoCommand = new Undo(history, checkersBoard);
        CRedoCommand = new Redo(history, checkersBoard);
    }

    @Override
    public void show(Coordinates piece, boolean multipleTake){
        CShowPossibilitiesCommand.setShow(piece, multipleTake);
        CShowPossibilitiesCommand.execute();
        if(multipleTake){
            this.notifyBoardObservers("ShowPossibilitesContinue", piece, CShowPossibilitiesCommand.getPlaces());
        }else{
            this.notifyBoardObservers("ShowPossibilites", piece, CShowPossibilitiesCommand.getPlaces());
        }
    }

    @Override
    public void move(Coordinates start, Coordinates end){
        CMoveCommand.setMove(start, end);
        CMoveCommand.execute();
        if(CMoveCommand.isMultipleTake()){
            this.notifyBoardObservers("MoveCanContinue", start, CMoveCommand.getPlaces());
        }else{
            this.notifyBoardObservers("Move", start, CMoveCommand.getPlaces());
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
