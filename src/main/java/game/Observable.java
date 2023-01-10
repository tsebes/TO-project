package game;

import gui.BoardObserver;

public interface Observable {

    void addBoardObserver(BoardObserver boardObserver);

    void removeBoardObserver(BoardObserver boardObserver);

    void notifyBoardObservers();
}
