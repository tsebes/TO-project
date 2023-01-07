package game.commands;

public class Undo implements Command {

    private final CommandHistory history;
    private Command undoneCommand;

    public Undo(CommandHistory history) {
        this.history = history;
    }

    @Override
    public void execute() {
        undoneCommand = history.pop();
        undoneCommand.undo();

        history.push(this);
        SaveCommands.getInstance().saveHistory("Undo");
    }

    @Override
    public void undo() {
        undoneCommand.execute();
        history.push(undoneCommand);
        SaveCommands.getInstance().saveHistory("Redo");
    }

    @Override
    public String toString() {
        return "Undo";
    }
}

