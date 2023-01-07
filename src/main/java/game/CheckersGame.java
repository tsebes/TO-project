package game;

import game.enums.Player;

import java.util.Set;

public class CheckersGame extends BoardGame {

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        if (isJumpMovePossible(currentTurn)) {
            possibleMoves.addAll(getPossibleJumps(piece));
        } else {
            possibleMoves.addAll(getPossibleMoves(piece));
        }
        notifyBoardObservers();
    }

    private boolean isJumpMovePossible(Player player) {
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

    private boolean isJumpPossible(Coordinates piece) {
        return !getPossibleJumps(piece).isEmpty();
    }

    @Override
    protected boolean canJump(Coordinates piece) {
        return ((getPossibleJumps(piece)).size() > 0);
    }

    @Override
    protected boolean jumped(Coordinates start, Coordinates end) {
        return (getTaken(start, end).size() > 0);
    }

    private Set<Coordinates> getPossibleMoves(Coordinates piece) {
        return board.getField(piece).getPiece().getPossibleMoves(board, piece);
    }

    private Set<Coordinates> getPossibleJumps(Coordinates piece) {
        return board.getField(piece).getPiece().getPossibleJumps(board, piece);
    }

    @Override
    public boolean gameEnded() {
        boolean noMoves = true;
        boolean blackWin = true;
        boolean whiteWin = true;

        for (int i = 0; i < Board.TILE_COUNT; i++) {
            for (int j = 0; j < Board.TILE_COUNT; j++) {
                if (board.getField(new Coordinates(i, j)).getPlayer() == currentTurn) {
                    if(getPossibleJumps(new Coordinates(i, j)).size() > 0 || getPossibleMoves(new Coordinates(i, j)).size() > 0){
                        noMoves = false;
                    }
                }
                if (board.getField(new Coordinates(i, j)).getPlayer() == Player.WHITE) {
                    blackWin = false;
                }
                if (board.getField(new Coordinates(i, j)).getPlayer() == Player.BLACK) {
                    whiteWin = false;
                }
            }
        }
        if(blackWin){
            winner = Player.BLACK;
            return true;
        }
        if(whiteWin){
            winner = Player.WHITE;
            return true;
        }
        if(noMoves){
            if(currentTurn == Player.WHITE){
                winner = Player.BLACK;
            }
            else{
                winner = Player.WHITE;
            }
            return true;
        }
        return false;
    }
}
