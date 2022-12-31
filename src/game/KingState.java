package game;

import game.enums.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KingState implements State {

    private final Board board;
    private final Player currentTurn;

    public KingState(Board board, Player player) {
        this.board = board;
        this.currentTurn = player;
    }

    @Override
    public Set<Coordinates> getPossibleMoves(Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();
        for (int i = 1; i < Board.TILE_COUNT; i++) {
            if (piece.x() - i >= 0 && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() - i, piece.y() + i));
            } else
                break;
        }
        for (int i = 1; i < Board.TILE_COUNT; i++) {
            if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() - i, piece.y() - i));
            } else
                break;
        }
        for (int i = 1; i < Board.TILE_COUNT; i++) {
            if (piece.x() + i < Board.TILE_COUNT && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() + i, piece.y() + i));
            } else
                break;
        }
        for (int i = 1; i < Board.TILE_COUNT; i++) {
            if (piece.x() + i < Board.TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty()) {
                possibleBasic.add(new Coordinates(piece.x() + i, piece.y() - i));
            } else
                break;
        }
        return possibleBasic;
    }


    @Override
    public Set<Coordinates> getPossibleJumps(Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();
        List<Coordinates> opponents = new ArrayList<>();
        for (int i = 1; i < Board.TILE_COUNT; i++) {
            if (piece.x() - i >= 0 && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty()) {
                opponents.add(new Coordinates(piece.x() - i, piece.y() + i));

                for (Coordinates opponent : opponents) {
                    for (int j = 1; opponent.x() - j >= 0 && opponent.y() + j < Board.TILE_COUNT && opponent.y() + j >= 0 && opponent.x() - j < Board.TILE_COUNT; j++) {
                        if (opponent.x() - j >= 0 && opponent.y() + j < Board.TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() + j)).isEmpty()) {
                            possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() + j));
                        } else
                            break;
                    }
                }
                break;
            } else if (piece.x() - i >= 0 && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).getPlayer() == currentTurn)
                break;
        }

        for (int i = 1; i < Board.TILE_COUNT; i++) {
            opponents.clear();
            if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty()) {
                opponents.add(new Coordinates(piece.x() - i, piece.y() - i));

                for (Coordinates opponent : opponents) {
                    for (int j = 1; opponent.x() - j >= 0 && opponent.y() - j < Board.TILE_COUNT && opponent.y() - j >= 0 && opponent.x() - j < Board.TILE_COUNT; j++) {
                        if (opponent.x() - j >= 0 && opponent.y() - j < Board.TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() - j)).isEmpty()) {
                            possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() - j));
                        } else
                            break;
                    }
                }
                break;
            } else if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).getPlayer() == currentTurn)
                break;
        }

        for (int i = 1; i < Board.TILE_COUNT; i++) {
            opponents.clear();

            if (piece.x() + i < Board.TILE_COUNT && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty()) {
                opponents.add(new Coordinates(piece.x() + i, piece.y() + i));

                for (Coordinates opponent : opponents) {
                    for (int j = 1; opponent.x() + j >= 0 && opponent.y() + j < Board.TILE_COUNT; j++) {
                        if (opponent.x() + j >= 0 && opponent.y() + j < Board.TILE_COUNT && opponent.y() + j >= 0 && opponent.x() + j < Board.TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() + j)).isEmpty()) {
                            possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() + j));
                        } else
                            break;
                    }
                }
                break;
            } else if (piece.x() + i < Board.TILE_COUNT && piece.y() + i < Board.TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).getPlayer() == currentTurn)
                break;
        }

        for (int i = 1; i < Board.TILE_COUNT; i++) {
            opponents.clear();
            if (piece.x() + i < Board.TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty()) {
                opponents.add(new Coordinates(piece.x() + i, piece.y() - i));

                for (Coordinates opponent : opponents) {
                    for (int j = 1; opponent.x() + j >= 0 && opponent.y() - j < Board.TILE_COUNT && opponent.y() - j >= 0 && opponent.x() + j < Board.TILE_COUNT; j++) {
                        if (opponent.x() + j >= 0 && opponent.y() - j < Board.TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() - j)).isEmpty()) {
                            possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() - j));
                        } else
                            break;
                    }
                }
                break;
            } else if (piece.x() + i < Board.TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).getPlayer() == currentTurn)
                break;
        }
        return possibleJump;
    }

}
