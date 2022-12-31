package game;

import game.enums.PieceType;
import game.enums.Player;

import java.util.Set;

public class CheckersGame extends BoardGame implements State {

    private KingState kingState;
    private ManState manState;

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        if (isJumpMovePossible(currentTurn)) {
            possibleMoves.addAll(getPossibleJumps(piece));
            notifyBoardObservers();
        } else if (isMovePossible(piece)) {
            possibleMoves.addAll(getPossibleMoves(piece));
            notifyBoardObservers();
        }
    }

    public boolean isJumpMovePossible(Player player) {
        for (int i = 0; i < Board.TILE_COUNT; i++) {
            for (int j = 0; j < Board.TILE_COUNT; j++) {
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
        return !getPossibleJumps(piece).isEmpty();
    }

    @Override
    public boolean isMovePossible(Coordinates piece) {
        return !getPossibleMoves(piece).isEmpty();
    }

    @Override
    public boolean canJump(Coordinates piece) {
        return ((getPossibleJumps(piece)).size() > 0);
    }

    @Override
    public boolean jumped(Coordinates start, Coordinates end) {
        return (getTaken(start, end).size() > 0);
    }

    @Override
    public Set<Coordinates> getPossibleMoves(Coordinates piece) {
        if (board.getField(piece).getPiece() == PieceType.MAN) {
            manState = new ManState(board);
            return manState.getPossibleMoves(piece);
        } else
            kingState = new KingState(board, currentTurn);
        return kingState.getPossibleMoves(piece);
    }

    @Override
    public Set<Coordinates> getPossibleJumps(Coordinates piece) {
        if (board.getField(piece).getPiece() == PieceType.MAN) {
            manState = new ManState(board);
            return manState.getPossibleJumps(piece);
        } else
            kingState = new KingState(board, currentTurn);
        return kingState.getPossibleJumps(piece);
    }
}
