package gui;

import game.Coordinates;

import java.util.List;

public interface BoardObserver {
    void boardUpdate(String action, Coordinates piece, List<Coordinates> places);
}
