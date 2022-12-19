package game.Checkers;


import java.util.Stack;

public class Undo extends Command{
    public Undo(Stack<String> history, CheckersBoard checkersBoard) {
        super(history, checkersBoard);
    }

    @Override
    public void execute() {

    }

    @Override
    public void updateHistory() {

    }
}
