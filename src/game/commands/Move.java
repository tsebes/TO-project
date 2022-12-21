package game.commands;

import game.Board;
import game.Coordinates;
import game.Field;

public class Move implements Command {

    private final CommandHistory history;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private Field piece;

    public Move(CommandHistory history, Board board, Coordinates start, Coordinates end) {
        this.history = history;
        this.board = board;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        Field[][] fields = board.getFields();
        piece = fields[start.x()][start.y()];
        fields[start.x()][start.y()] = new Field();
        fields[end.x()][end.y()] = piece;
        history.push(this);
    }

    @Override
    public void undo() {
        // TODO odtworzenie zbitego pionka
        Field[][] fields = board.getFields();
        fields[start.x()][start.y()] = piece;
        fields[end.x()][end.y()] = new Field();
    }
}
