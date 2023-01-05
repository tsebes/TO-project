package game;

import game.enums.PieceType;

import java.util.HashSet;
import java.util.Set;

import static game.Board.TILE_COUNT;

public class KnightsGame extends BoardGame {

    public KnightsGame(Board board) {
        super(board);
        System.out.println("Created new Knights game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        possibleMoves.addAll(basicMoves(piece));
        possibleMoves.addAll(jumpMoves(piece));
        for (Coordinates place : jumpMoves(piece)) {
            addMultipleJumps(place);
        }
        notifyBoardObservers();
    }

    private void addMultipleJumps(Coordinates piece) {
        for (Coordinates place : jumpMoves(piece)) {
            if (!possibleMoves.contains(place)) {
                possibleMoves.add(place);
                addMultipleJumps(place);
            }
        }
    }

    private Set<Coordinates> basicMoves(Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();
        if (piece.x() > 0 && board.getField(new Coordinates(piece.x() - 1, piece.y())).isEmpty()) {
            possibleBasic.add(new Coordinates(piece.x() - 1, piece.y()));
        }
        if (piece.x() < TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() + 1, piece.y())).isEmpty()) {
            possibleBasic.add(new Coordinates(piece.x() + 1, piece.y()));
        }
        if (piece.y() > 0 && board.getField(new Coordinates(piece.x(), piece.y() - 1)).isEmpty()) {
            possibleBasic.add(new Coordinates(piece.x(), piece.y() - 1));
        }
        if (piece.y() < TILE_COUNT - 1 && board.getField(new Coordinates(piece.x(), piece.y() + 1)).isEmpty()) {
            possibleBasic.add(new Coordinates(piece.x(), piece.y() + 1));
        }
        return possibleBasic;
    }

    private Set<Coordinates> jumpMoves(Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();
        if (piece.x() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y())).getPieceType() == PieceType.MAN && board.getField(new Coordinates(piece.x() - 2, piece.y())).isEmpty()) {
            possibleJump.add(new Coordinates(piece.x() - 2, piece.y()));
        }
        if (piece.x() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y())).getPieceType() == PieceType.MAN && board.getField(new Coordinates(piece.x() + 2, piece.y())).isEmpty()) {
            possibleJump.add(new Coordinates(piece.x() + 2, piece.y()));
        }
        if (piece.y() > 1 && board.getField(new Coordinates(piece.x(), piece.y() - 1)).getPieceType() == PieceType.MAN && board.getField(new Coordinates(piece.x(), piece.y() - 2)).isEmpty()) {
            possibleJump.add(new Coordinates(piece.x(), piece.y() - 2));
        }
        if (piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x(), piece.y() + 1)).getPieceType() == PieceType.MAN && board.getField(new Coordinates(piece.x(), piece.y() + 2)).isEmpty()) {
            possibleJump.add(new Coordinates(piece.x(), piece.y() + 2));
        }
        return possibleJump;
    }

    @Override
    protected boolean canJump(Coordinates piece) {
        return ((jumpMoves(piece)).size() > 0);
    }

    @Override
    protected boolean jumped(Coordinates start, Coordinates end) {
        return false;
    }

}
