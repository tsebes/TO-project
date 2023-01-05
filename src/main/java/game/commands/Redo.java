package game.commands;

import game.BoardGame;
import game.Coordinates;

public class Redo implements Command {
    private final CommandHistory history;
    private final BoardGame game;
    private final Coordinates start;
    private final Coordinates end;

    public Redo(CommandHistory history, BoardGame game, Coordinates start, Coordinates end) {
        this.history = history;
        this.game = game;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        Command last = history.peek();
        if(last.getClass().equals(Undo.class)) {
            game.move(start, end);
            history.pop();
            history.push(this);
            SaveCommands.getInstance().saveHistory( this);
        }
    }
}
