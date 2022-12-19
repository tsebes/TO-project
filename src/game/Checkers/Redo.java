package game.Checkers;

import java.util.Stack;

public class Redo extends Command {
    public Redo(Stack<String> history, CheckersBoard checkersBoard) {
        super(history, checkersBoard);
    }

    @Override
    public void execute() {

    }

    @Override
    public void updateHistory() {

    }
}
