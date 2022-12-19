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
    private ShowPossibilities showPossibilitiesCommand;
    private Move moveCommand;
    private Undo undoCommand;
    private Redo redoCommand;
    private CheckersBoard checkersBoard;

    @Override
    public void start() {
        System.out.println("Checkers game - start");
        history = new Stack<String>();
        checkersBoard = new CheckersBoard();
        showPossibilitiesCommand = new ShowPossibilities(history, checkersBoard);
        moveCommand = new Move(history, checkersBoard);
        undoCommand = new Undo(history, checkersBoard);
        redoCommand = new Redo(history, checkersBoard);
    }

    @Override
    public void show(Coordinates piece, boolean multipleTake){
        showPossibilitiesCommand.setShow(piece, multipleTake);
        showPossibilitiesCommand.execute();
        if(multipleTake){
            this.notifyBoardObservers("ShowPossibilitesContinue", piece, showPossibilitiesCommand.getPlaces());
        }else{
            this.notifyBoardObservers("ShowPossibilites", piece, showPossibilitiesCommand.getPlaces());
        }
    }

    @Override
    public void move(Coordinates start, Coordinates end){
        moveCommand.setMove(start, end);
        moveCommand.execute();
        if(moveCommand.isMultipleTake()){
            this.notifyBoardObservers("MoveCanContinue", start, moveCommand.getPlaces());
        }else{
            this.notifyBoardObservers("Move", start, moveCommand.getPlaces());
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
