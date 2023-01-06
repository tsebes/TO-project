package game.commands;

public class Undo implements Command {

    private final CommandHistory history;

    public Undo(CommandHistory history) {
        this.history = history;
    }

    @Override
    public void execute() {
        history.pop();
        history.push(this);
        SaveCommands.getInstance().saveHistory(this);
    }

    @Override
    public String toString() {
        return "Undo";
    }
}

