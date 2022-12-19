package game.Knights;

import game.Checkers.CheckersBoard;

import java.util.Stack;

public class KRedo extends KCommand{
    public KRedo(Stack<String> history, KnightsBoard knightsBoard) {
        super(history, knightsBoard);
    }

    @Override
    public void execute() {

    }

    @Override
    public void updateHistory() {

    }
}
