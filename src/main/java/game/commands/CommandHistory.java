package game.commands;

import java.util.Stack;

public class CommandHistory {

    private final Stack<Command> history = new Stack<>();

    public void push(Command command) {
        history.push(command);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean canUndo() {
        return !history.isEmpty() && !history.peek().getClass().equals(Undo.class);
    }

    public boolean canRedo() {
        return !history.isEmpty() && history.peek().getClass().equals(Undo.class);
    }
}
