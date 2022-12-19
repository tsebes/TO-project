package game.Knights;

import java.util.Stack;

public class KUndo extends KCommand{
    public KUndo(Stack<String> history, KnightsBoard knightsBoard) {
        super(history, knightsBoard);
    }

    @Override
    public void execute() {

    }

    @Override
    public void updateHistory() {

    }
}
