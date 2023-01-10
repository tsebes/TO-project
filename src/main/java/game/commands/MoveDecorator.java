package game.commands;

import game.Board;
import game.Coordinates;
import game.Piece;
import game.enums.PieceType;
import game.enums.Player;

public class MoveDecorator implements Command {

    private final Move move;
    private final Board board;
    private final Coordinates start;
    private final Coordinates end;
    private final Piece piece;
    private final Coordinates takenPieceCoordinates;
    private Piece takenPiece;
    private boolean becameKing;

    public MoveDecorator(Move move, Coordinates takenPieceCoordinates) {
        this.move = move;
        this.board = move.getBoard();
        this.start = move.getStart();
        this.end = move.getEnd();
        this.piece = move.getPiece();
        this.takenPieceCoordinates = takenPieceCoordinates;
    }

    @Override
    public void execute() {
        move.execute();
        becomeKing();

        if (takenPieceCoordinates != null) {
            takenPiece = board.getField(takenPieceCoordinates).getPiece();
            board.getField(takenPieceCoordinates).removePiece();
            board.setMultipleTake(move.getGame().canJump(end));
        } else {
            board.setMultipleTake(false);
        }

        if (board.isMultipleTake()) {
            move.getGame().changeTurn();
        }
        board.setCurrent(end);

        SaveCommands.getInstance().saveHistory(this);
    }

    @Override
    public void undo() {
        move.undo();
        if (takenPieceCoordinates != null) {
            board.getField(takenPieceCoordinates).setPiece(takenPiece);
        }
    }

    private void becomeKing() {
        becameKing = false;

        if (piece.getPieceType() == PieceType.MAN && end.x() == 0 && piece.getPlayer() == Player.WHITE) {
            board.getField(end).setPiece(new Piece(Player.WHITE, PieceType.KING));
            becameKing = true;
        } else if (piece.getPieceType() == PieceType.MAN && end.x() == 7 && piece.getPlayer() == Player.BLACK) {
            board.getField(end).setPiece(new Piece(Player.BLACK, PieceType.KING));
            becameKing = true;
        }
    }

    @Override
    public String toString() {
        String string = move.toString();

        if (takenPieceCoordinates != null) {
            string = string + " and took " + takenPiece.getPieceType();
        }
        if (becameKing) {
            string = string + " and became a king";
        }

        return string;
    }
}
