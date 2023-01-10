package game.commands;

import game.Board;
import game.BoardGame;
import game.Coordinates;
import game.Piece;
import game.enums.Player;

public class Move implements Command {

    private final BoardGame game;
    private final Board board;

    private final Coordinates start;
    private final Coordinates end;
    private final Piece piece;

    private Player currentTurn;
    private Coordinates current;
    private boolean multipleTake;

    public Move(BoardGame game, Coordinates start, Coordinates end) {
        this.game = game;
        this.board = game.getBoard();
        this.start = start;
        this.end = end;
        this.piece = board.getField(start).getPiece();
    }

    @Override
    public void execute() {
        board.getField(start).removePiece();
        board.getField(end).setPiece(piece);

        currentTurn = game.getCurrentTurn();
        current = board.getCurrent();
        multipleTake = board.isMultipleTake();

        game.changeTurn();

        SaveCommands.getInstance().saveHistory(this);
    }

    @Override
    public void undo() {
        board.getField(start).setPiece(piece);
        board.getField(end).removePiece();

        game.setCurrentTurn(currentTurn);
        board.setCurrent(current);
        board.setMultipleTake(multipleTake);
    }

    @Override
    public String toString() {
        return piece.getPlayer() + " " + piece.getPieceType() + " moved from " + start + " to " + end;
    }

    public BoardGame getGame() {
        return game;
    }

    public Board getBoard() {
        return board;
    }

    public Coordinates getEnd() {
        return end;
    }

    public Piece getPiece() {
        return piece;
    }
}
