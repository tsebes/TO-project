package game;

import gui.BoardObserver;

import java.util.List;

public interface BoardGame{
    void start();
    void show(Coordinates piece, boolean multipleTake);
    void move(Coordinates start, Coordinates end);

    void addBoardObserver(BoardObserver boardObserver);

    void removeBoardObserver(BoardObserver boardObserver);

    void notifyBoardObservers(String action, Coordinates piece, List<Coordinates> places);
}
