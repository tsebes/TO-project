package game.commands;

import game.Board;
import game.Coordinates;
import game.Field;
import game.Piece;
import game.enums.PieceType;
import game.enums.Player;

public class Move implements Command {

    private final CommandHistory history;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private final Coordinates takenPiece;
    private Piece piece;
    private PieceType takenType;
    private boolean becameKing;

    public Move(CommandHistory history, Board board, Coordinates start, Coordinates end, Coordinates takenPiece, PieceType takenType) {
        this.history = history;
        this.board = board;
        this.start = start;
        this.end = end;
        this.takenPiece = takenPiece;
        this.takenType = takenType;
    }

    @Override
    public void execute() {
        Field[][] fields = board.getFields();
        piece = fields[start.x()][start.y()].getPiece();
        fields[start.x()][start.y()].removePiece();
        fields[end.x()][end.y()].setPiece(piece);
        becomeKing();

        if (takenPiece != null)
            fields[takenPiece.x()][takenPiece.y()].removePiece();

        history.push(this);
        SaveCommands.getInstance().saveHistory(this.toString());
    }

    @Override
    public void undo() {
        Field[][] fields = board.getFields();
        piece = fields[end.x()][end.y()].getPiece();
        if (becameKing) {
            piece = new Piece(piece.getPlayer(), PieceType.MAN);
        }
        fields[start.x()][start.y()].setPiece(piece);
        fields[end.x()][end.y()].removePiece();

        if (takenPiece != null) {
            if (piece.getPlayer() == Player.WHITE && takenType == PieceType.MAN)
                fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.BLACK, PieceType.MAN));
            else if (piece.getPlayer() == Player.WHITE && takenType == PieceType.KING)
                fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.BLACK, PieceType.KING));
            else if (piece.getPlayer() == Player.BLACK && takenType == PieceType.KING)
                fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.WHITE, PieceType.KING));
            else
                fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.WHITE, PieceType.MAN));
        }
    }


    private void becomeKing() {
        Field[][] fields = board.getFields();
        becameKing = false;

        if (piece.getPieceType() == PieceType.MAN && end.x() == 0 && piece.getPlayer() == Player.WHITE) {
            fields[end.x()][end.y()].setPiece(new Piece(Player.WHITE, PieceType.KING));
            becameKing = true;
        } else if (piece.getPieceType() == PieceType.MAN && end.x() == 7 && piece.getPlayer() == Player.BLACK) {
            fields[end.x()][end.y()].setPiece(new Piece(Player.BLACK, PieceType.KING));
            becameKing = true;
        }
    }

    @Override
    public String toString() {
        if (becameKing)
            return piece.getPlayer() + " " + piece.getPieceType() + " moved from " + start + " to " + end + " and became a king";
        else if (takenPiece != null)
            return piece.getPlayer() + " " + piece.getPieceType() + " moved from " + start + " to " + end + " and took " + takenType;
        else
            return piece.getPlayer() + " " + piece.getPieceType() + " moved from " + start + " to " + end;
    }
}
