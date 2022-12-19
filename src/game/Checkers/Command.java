package game.Checkers;

import gui.Observable;
import gui.Observer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public abstract class Command{
    public Stack<String> history = new Stack<String>();
    public CheckersBoard checkersBoard = new CheckersBoard();
    private final Set<Observer> observers = new HashSet<>();

    abstract void execute();

    abstract void updateHistory();

    Command(Stack<String> history, CheckersBoard checkersBoard){
        this.history = history;
        this.checkersBoard = checkersBoard;
    }

    public CheckersBoard getCheckersBoard() {
        return checkersBoard;
    }
}
