package game;

import game.enums.Player;

import java.util.HashSet;
import java.util.Set;

public class ManStrategy implements Strategy {

    @Override
    public Set<Coordinates> getPossibleMoves(Board board, Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();

        if (board.getField(piece).getPlayer() == Player.WHITE) {
            if (piece.x() > 0 && piece.y() < Board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() + 1));
            }
            if (piece.x() > 0 && piece.y() > 0 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() - 1));
            }
        } else {
            if (piece.x() < Board.TILE_COUNT - 1 && piece.y() < Board.TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() + 1));
            }
            if (piece.x() < Board.TILE_COUNT - 1 && piece.y() > 0 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() - 1));
            }
        }
        return possibleBasic;
    }

    @Override
    public Set<Coordinates> getPossibleJumps(Board board, Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();

        if (board.getField(piece).getPlayer() == Player.WHITE) {
            if (piece.x() > 1 && piece.y() < Board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
            }
            if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
            }
            if (board.isMultipleTake()) {
                if (piece.x() < Board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                    possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                }
                if (piece.x() < Board.TILE_COUNT - 2 && piece.y() < Board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                    possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                }
            }
        } else {
            if (piece.x() < Board.TILE_COUNT - 2 && piece.y() < Board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
            }
            if (piece.x() < Board.TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
            }
            if (board.isMultipleTake()) {
                if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                    possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                }
                if (piece.x() > 1 && piece.y() < Board.TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                    possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                }
            }
        }
        return possibleJump;
    }

}

