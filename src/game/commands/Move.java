package game.commands;

import game.Board;
import game.Coordinates;
import game.Field;
import game.Piece;
import game.enums.PieceType;
import game.enums.Player;

import java.util.Set;

public class Move implements Command {

    private final CommandHistory history;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private final Set<Coordinates> taken;
    private Piece piece;

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
        piece = fields[start.x()][start.y()].getPiece();
        fields[start.x()][start.y()].removePiece();
        fields[end.x()][end.y()].setPiece(piece);
        becomeKing();
        for (Coordinates coordinates : taken) {
            fields[coordinates.x()][coordinates.y()].removePiece();
        }
        history.push(this);
    }

    @Override
    public void undo() {
        // TODO odtworzenie zbitego pionka
        Field[][] fields = board.getFields();
        fields[start.x()][start.y()].setPiece(piece);
        fields[end.x()][end.y()].removePiece();
    }

    public void becomeKing() {
        Field[][] fields = board.getFields();
        if (piece.getPieceType() == PieceType.MAN && end.x() == 0 && piece.getPlayer() == Player.WHITE) {
            fields[end.x()][end.y()].setPiece(new Piece(Player.WHITE, PieceType.KING));
        } else if (piece.getPieceType() == PieceType.MAN && end.x() == 7 && piece.getPlayer() == Player.BLACK) {
            fields[end.x()][end.y()].setPiece(new Piece(Player.BLACK, PieceType.KING));
        }
    }
}
