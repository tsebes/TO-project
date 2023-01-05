package game.commands;

import game.Board;
import game.Coordinates;
import game.Field;
import game.Piece;
import game.enums.PieceType;
import game.enums.Player;

public class Undo implements Command {

    private final CommandHistory history;
    private final Coordinates takenPiece;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private Piece piece;
    private PieceType takenType;

    public Undo(CommandHistory history,Board board, Coordinates start, Coordinates end, Coordinates takenPiece, PieceType takenType) {
        this.history = history;
        this.board = board;
        this.start = start;
        this.end = end;
        this.takenPiece = takenPiece;
        this.takenType = takenType;
    }

    @Override
    public void execute() {
        Command last = history.peek();
        if(last.getClass().equals(Move.class)) {
            Field[][] fields = board.getFields();
            piece = fields[end.x()][end.y()].getPiece();
            fields[start.x()][start.y()].setPiece(piece);
            fields[end.x()][end.y()].removePiece();

            if (takenPiece != null) {
                if(piece.getPlayer()== Player.WHITE && takenType == PieceType.MAN)
                    fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.BLACK, PieceType.MAN));
                else if(piece.getPlayer()== Player.WHITE && takenType == PieceType.KING)
                    fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.BLACK, PieceType.KING));
                else if(piece.getPlayer()== Player.BLACK &&  takenType== PieceType.KING)
                    fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.WHITE, PieceType.KING));
                else
                    fields[takenPiece.x()][takenPiece.y()].setPiece(new Piece(Player.WHITE, PieceType.MAN));
            }
            history.push(this);
            SaveCommands.getInstance().saveHistory( this);
        }
    }
}

