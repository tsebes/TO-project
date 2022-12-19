package game.Knights;

import gui.Observer;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public abstract class KCommand {
    public Stack<String> history = new Stack<String>();
    public KnightsBoard knightsBoard = new KnightsBoard();
    private final Set<Observer> observers = new HashSet<>();

    abstract void execute();

    abstract void updateHistory();

    KCommand(Stack<String> history, KnightsBoard knightsBoard){
        this.history = history;
        this.knightsBoard = knightsBoard;
    }

    public KnightsBoard getKnightsBoard() {
        return knightsBoard;
    }
}