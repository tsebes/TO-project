package game;

import gui.BoardObserver;

public interface Observable {

    void addBoardObserver(BoardObserver boardObserver);

    void removeBoardObserver(BoardObserver boardObserver);

    void notifyBoardObservers();

    void move(Coordinates lastClicked, Coordinates coordinates);
}
