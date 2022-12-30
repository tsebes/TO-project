package game;


import game.enums.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static game.Board.TILE_COUNT;

public class CheckersGame extends BoardGame {

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        if (isJumpMovePossible(currentTurn)) {
            possibleMoves.addAll(jumpMoves(piece));
            notifyBoardObservers();
        } else if (isMovePossible(piece)) {
            possibleMoves.addAll(basicMoves(piece));
            notifyBoardObservers();
        }
    }

    private Set<Coordinates> basicMoves(Coordinates piece) {
        Set<Coordinates> possibleBasic = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if (board.getField(piece).getPlayer() == Player.WHITE) {
                    if (piece.x() > 0 && piece.y() < TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() + 1));
                    }
                    if (piece.x() > 0 && piece.y() > 0 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - 1, piece.y() - 1));
                    }
                } else {
                    if (piece.x() < TILE_COUNT - 1 && piece.y() < TILE_COUNT - 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() + 1));
                    }
                    if (piece.x() < TILE_COUNT - 1 && piece.y() > 0 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + 1, piece.y() - 1));
                    }
                }
            }
            case KING -> {

                for (int i = 1; i < TILE_COUNT; i++) {
                    if (piece.x() - i >= 0 && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - i, piece.y() + i));
                    } else
                        break;
                }
                for (int i = 1; i < TILE_COUNT; i++) {
                    if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() - i, piece.y() - i));
                    } else
                        break;
                }
                for (int i = 1; i < TILE_COUNT; i++) {
                    if (piece.x() + i < TILE_COUNT && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + i, piece.y() + i));
                    } else
                        break;
                }
                for (int i = 1; i < TILE_COUNT; i++) {
                    if (piece.x() + i < TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty()) {
                        possibleBasic.add(new Coordinates(piece.x() + i, piece.y() - i));
                    } else
                        break;
                }
            }
        }
        return possibleBasic;
    }

    private Set<Coordinates> jumpMoves(Coordinates piece) {
        Set<Coordinates> possibleJump = new HashSet<>();
        switch (board.getField(piece).getPiece()) {
            case MAN -> {
                if (board.getField(piece).getPlayer() == Player.WHITE) {
                    if (piece.x() > 1 && piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                    }
                    if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                    }
                    if (board.isMultipleTake()) {
                        if (piece.x() < TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                        }
                        if (piece.x() < TILE_COUNT - 2 && piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.BLACK && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                        }
                    }
                } else {
                    if (piece.x() < TILE_COUNT - 2 && piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() + 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() + 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() + 2));
                    }
                    if (piece.x() < TILE_COUNT - 2 && piece.y() > 1 && board.getField(new Coordinates(piece.x() + 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() + 2, piece.y() - 2)).isEmpty()) {
                        possibleJump.add(new Coordinates(piece.x() + 2, piece.y() - 2));
                    }
                    if (board.isMultipleTake()) {
                        if (piece.x() > 1 && piece.y() > 1 && board.getField(new Coordinates(piece.x() - 1, piece.y() - 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() - 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() - 2, piece.y() - 2));
                        }
                        if (piece.x() > 1 && piece.y() < TILE_COUNT - 2 && board.getField(new Coordinates(piece.x() - 1, piece.y() + 1)).getPlayer() == Player.WHITE && board.getField(new Coordinates(piece.x() - 2, piece.y() + 2)).isEmpty()) {
                            possibleJump.add(new Coordinates(piece.x() - 2, piece.y() + 2));
                        }
                    }
                }

            }
            case KING -> {
                List<Coordinates> opponents = new ArrayList<>();
                for (int i = 1; i < TILE_COUNT; i++) {
                    if (piece.x() - i >= 0 && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() - i, piece.y() + i)).isEmpty()) {
                        opponents.add(new Coordinates(piece.x() - i, piece.y() + i));

                        for (Coordinates opponent : opponents) {
                            for (int j = 1; opponent.x() - j >= 0 && opponent.y() + j < TILE_COUNT && opponent.y() + j >= 0 && opponent.x() - j < TILE_COUNT; j++) {
                                if (opponent.x() - j >= 0 && opponent.y() + j < TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() + j)).isEmpty()) {
                                    possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() + j));
                                } else
                                    break;
                            }
                        }
                        break;
                    } else if (piece.x() - i >= 0 && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() - i, piece.y() + i)).getPlayer() == currentTurn)
                        break;
                }

                for (int i = 1; i < TILE_COUNT; i++) {
                    opponents.clear();
                    if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() - i, piece.y() - i)).isEmpty()) {
                        opponents.add(new Coordinates(piece.x() - i, piece.y() - i));

                        for (Coordinates opponent : opponents) {
                            for (int j = 1; opponent.x() - j >= 0 && opponent.y() - j < TILE_COUNT && opponent.y() - j >= 0 && opponent.x() - j < TILE_COUNT; j++) {
                                if (opponent.x() - j >= 0 && opponent.y() - j < TILE_COUNT && board.getField(new Coordinates(opponent.x() - j, opponent.y() - j)).isEmpty()) {
                                    possibleJump.add(new Coordinates(opponent.x() - j, opponent.y() - j));
                                } else
                                    break;
                            }
                        }
                        break;
                    } else if (piece.x() - i >= 0 && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() - i, piece.y() - i)).getPlayer() == currentTurn)
                        break;
                }

                for (int i = 1; i < TILE_COUNT; i++) {
                    opponents.clear();

                    if (piece.x() + i < TILE_COUNT && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() + i, piece.y() + i)).isEmpty()) {
                        opponents.add(new Coordinates(piece.x() + i, piece.y() + i));

                        for (Coordinates opponent : opponents) {
                            for (int j = 1; opponent.x() + j >= 0 && opponent.y() + j < TILE_COUNT; j++) {
                                if (opponent.x() + j >= 0 && opponent.y() + j < TILE_COUNT && opponent.y() + j >= 0 && opponent.x() + j < TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() + j)).isEmpty()) {
                                    possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() + j));
                                } else
                                    break;
                            }
                        }
                        break;
                    } else if (piece.x() + i < TILE_COUNT && piece.y() + i < TILE_COUNT && board.getField(new Coordinates(piece.x() + i, piece.y() + i)).getPlayer() == currentTurn)
                        break;
                }

                for (int i = 1; i < TILE_COUNT; i++) {
                    opponents.clear();
                    if (piece.x() + i < TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).getPlayer() != currentTurn && !board.getField(new Coordinates(piece.x() + i, piece.y() - i)).isEmpty()) {
                        opponents.add(new Coordinates(piece.x() + i, piece.y() - i));

                        for (Coordinates opponent : opponents) {
                            for (int j = 1; opponent.x() + j >= 0 && opponent.y() - j < TILE_COUNT && opponent.y() - j >= 0 && opponent.x() + j < TILE_COUNT; j++) {
                                if (opponent.x() + j >= 0 && opponent.y() - j < TILE_COUNT && board.getField(new Coordinates(opponent.x() + j, opponent.y() - j)).isEmpty()) {
                                    possibleJump.add(new Coordinates(opponent.x() + j, opponent.y() - j));
                                } else
                                    break;
                            }
                        }
                        break;
                    } else if (piece.x() + i < TILE_COUNT && piece.y() - i >= 0 && board.getField(new Coordinates(piece.x() + i, piece.y() - i)).getPlayer() == currentTurn)
                        break;
                }
            }
        }

        return possibleJump;
    }

    public boolean isJumpMovePossible(Player player) {
        for (int i = 0; i < TILE_COUNT; i++) {
            for (int j = 0; j < TILE_COUNT; j++) {
                if (board.getField(new Coordinates(i, j)).getPlayer() == player) {
                    if (isJumpPossible(new Coordinates(i, j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isJumpPossible(Coordinates piece) {
        return !jumpMoves(piece).isEmpty();
    }

    @Override
    public boolean isMovePossible(Coordinates piece) {
        return !basicMoves(piece).isEmpty();
    }

    @Override
    public boolean canJump(Coordinates piece) {
        return ((jumpMoves(piece)).size() > 0);
    }

    @Override
    public boolean jumped(Coordinates start, Coordinates end) {
        return (getTaken(start, end).size() > 0);
    }
}
