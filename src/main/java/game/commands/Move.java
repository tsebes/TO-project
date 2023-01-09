package game.commands;

import game.*;

public class Move implements Command {

    private final CommandHistory history;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private Piece piece;

    public Move(CommandHistory history, Board board, Coordinates start, Coordinates end) {
        this.history = history;
        this.board = board;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        Field[][] fields = board.getFields();
        piece = fields[start.x()][start.y()].getPiece();
        fields[start.x()][start.y()].removePiece();
        fields[end.x()][end.y()].setPiece(piece);

        history.push(this);
        SaveCommands.getInstance().saveHistory(this);
    }

    @Override
    public void undo() {
        Field[][] fields = board.getFields();
        piece = fields[end.x()][end.y()].getPiece();
        fields[start.x()][start.y()].setPiece(piece);
        fields[end.x()][end.y()].removePiece();
    }

    @Override
    public String toString() {
        return piece.getPlayer() + " " + piece.getPieceType() + " moved from " + start + " to " + end;
    }
}
