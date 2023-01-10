package game;

import game.commands.Command;
import game.commands.Move;
import game.enums.PieceType;
import game.enums.Player;

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
    public boolean canJump(Coordinates piece) {
        return ((jumpMoves(piece)).size() > 0);
    }

    @Override
    public boolean gameEnded() {
        boolean blackWin = true;
        boolean whiteWin = true;

        for (int i = 0; i < Board.TILE_COUNT; i++) {
            for (int j = 0; j < Board.TILE_COUNT; j++) {
                if (i < 6 && board.getField(new Coordinates(i, j)).getPlayer() == Player.BLACK) {
                    blackWin = false;
                }
                if (i > 1 && board.getField(new Coordinates(i, j)).getPlayer() == Player.WHITE) {
                    whiteWin = false;
                }
            }
        }
        if (blackWin) {
            winner = Player.BLACK;
            return true;
        }
        if (whiteWin) {
            winner = Player.WHITE;
            return true;
        }
        return false;
    }

    @Override
    public void move(Coordinates start, Coordinates end) {
        Command moveCommand = new Move(this, start, end);
        moveCommand.execute();
        commandHistory.push(moveCommand);
        possibleMoves.clear();
        notifyBoardObservers();
    }
}
