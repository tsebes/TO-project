package game;

import gui.BoardObserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KnightsGame implements BoardGame {

    private final Set<BoardObserver> observers = new HashSet<>();

    @Override
    public void start() {
        System.out.println("Knights game - start");
    }

    @Override
    public void show(Coordinates piece, boolean multipleTake){
        //function to add
    }

    @Override
    public void move(Coordinates start, Coordinates end){
        //function to add
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
