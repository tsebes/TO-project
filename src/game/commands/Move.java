package game.commands;

import game.Board;
import game.Coordinates;
import game.Field;

import java.util.Set;

public class Move implements Command {

    private final CommandHistory history;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private final Set<Coordinates> taken;
    private Field piece;

    public Move(CommandHistory history, Board board, Coordinates start, Coordinates end, Set<Coordinates> taken) {
        this.history = history;
        this.board = board;
        this.start = start;
        this.end = end;
        this.taken = taken;
    }

    @Override
    public void execute() {
        Field[][] fields = board.getFields();
        piece = fields[start.x()][start.y()];
        fields[start.x()][start.y()] = new Field();
        fields[end.x()][end.y()] = piece;
        for(Coordinates coordinates: taken){
            fields[coordinates.x()][coordinates.y()] = new Field();
        }
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
