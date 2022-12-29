package game;

import game.enums.PieceType;

import java.util.HashSet;
import java.util.Set;

import static game.Board.TILE_COUNT;

public class KnightsGame extends BoardGame {
    private Coordinates lastPlace = new Coordinates(0, 0);

    public KnightsGame(Board board) {
        super(board);
        System.out.println("Created new Knights game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        if (!board.isMultipleTake()) {
            possibleMoves.addAll(basicMoves(piece));
        }
        possibleMoves.addAll(jumpMoves(piece));
        lastPlace = piece;
        notifyBoardObservers();
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
        if (piece.x() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y())).getPiece() == PieceType.MAN && board.getField(new Coordinates(piece.x() - 2, piece.y())).isEmpty() && (lastPlace.x() != piece.x() - 2 || lastPlace.y() != piece.y())) {
            possibleJump.add(new Coordinates(piece.x() - 2, piece.y()));
        }
        if (piece.x() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y())).getPiece() == PieceType.MAN && board.getField(new Coordinates(piece.x() + 2, piece.y())).isEmpty() && (lastPlace.x() != piece.x() + 2 || lastPlace.y() != piece.y())) {
            possibleJump.add(new Coordinates(piece.x() + 2, piece.y()));
        }
        if (piece.y() > 1 && board.getField(new Coordinates(piece.x(), piece.y() - 1)).getPiece() == PieceType.MAN && board.getField(new Coordinates(piece.x(), piece.y() - 2)).isEmpty() && (lastPlace.x() != piece.x() || lastPlace.y() != piece.y() - 2)) {
            possibleJump.add(new Coordinates(piece.x(), piece.y() - 2));
        }
        if (piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x(), piece.y() + 1)).getPiece() == PieceType.MAN && board.getField(new Coordinates(piece.x(), piece.y() + 2)).isEmpty() && (lastPlace.x() != piece.x() || lastPlace.y() != piece.y() + 2)) {
            possibleJump.add(new Coordinates(piece.x(), piece.y() + 2));
        }
        return possibleJump;
    }

    @Override
    public boolean canJump(Coordinates piece) {
        return ((jumpMoves(piece)).size() > 0);
    }

    @Override
    public boolean jumped(Coordinates start, Coordinates end) {
        return Math.abs(start.x() - end.x()) > 1 || Math.abs(start.y() - end.y()) > 1;
    }

    @Override
    public boolean isMovePossible(Coordinates piece) {
        return false;
    }

    @Override
    public boolean isJumpPossible(Coordinates piece) {
        return false;
    }

}
