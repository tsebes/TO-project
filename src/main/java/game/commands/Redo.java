package game.commands;

public class Redo implements Command {
    private final CommandHistory history;

    public Redo(CommandHistory history) {
        this.history = history;
    }

    @Override
    public void execute() {
        history.push(this);
        SaveCommands.getInstance().saveHistory(this);
    }

    @Override
    public String toString() {
        return "Redo";
    }

}
