package game;

import game.commands.Command;
import game.commands.Move;
import game.commands.MoveDecorator;
import game.enums.PieceType;
import game.enums.Player;

import java.util.HashSet;
import java.util.Set;

public class CheckersGame extends BoardGame {

    private PieceType takenType;

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
                    if (getPossibleJumps(new Coordinates(i, j)).size() > 0 || getPossibleMoves(new Coordinates(i, j)).size() > 0) {
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
        if (blackWin) {
            winner = Player.BLACK;
            return true;
        }
        if (whiteWin) {
            winner = Player.WHITE;
            return true;
        }
        if (noMoves) {
            if (currentTurn == Player.WHITE) {
                winner = Player.BLACK;
            } else {
                winner = Player.WHITE;
            }
            return true;
        }
        return false;
    }

    protected Set<Coordinates> getTaken(Coordinates start, Coordinates end) {
        Set<Coordinates> takenPieces = new HashSet<>();
        Coordinates temp;
        int xAxis = Math.abs(start.x() - end.x());
        int yAxis = Math.abs(start.y() - end.y());
        int j = Math.max(xAxis, yAxis);

        for (int i = 1; i < j; i++) {
            if (end.x() > start.x() && end.y() > start.y()) {
                temp = new Coordinates(start.x() + i, start.y() + i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                    takenType = board.getField(temp).getPieceType();
                }
            }
            if (end.x() > start.x() && end.y() < start.y()) {
                temp = new Coordinates(start.x() + i, start.y() - i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                    takenType = board.getField(temp).getPieceType();
                }
            }
            if (end.x() < start.x() && end.y() > start.y()) {
                temp = new Coordinates(start.x() - i, start.y() + i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                    takenType = board.getField(temp).getPieceType();
                }
            }
            if (end.x() < start.x() && end.y() < start.y()) {
                temp = new Coordinates(start.x() - i, start.y() - i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                    takenType = board.getField(temp).getPieceType();
                }
            }
        }
        return takenPieces;
    }

    public Coordinates getLast(Set<Coordinates> set) {
        System.out.println(set.size());
        Coordinates last = null;
        for (Coordinates c : set) {
            last = c;
        }
        return last;
    }

    @Override
    public void move(Coordinates start, Coordinates end) {
        boolean jumpingMove = jumped(start, end);
        Command moveCommand = new MoveDecorator(commandHistory, board, start, end, getLast(getTaken(start, end)), takenType);
        moveCommand.execute();
        if (jumpingMove) {
            board.setMultipleTake(canJump(end));
        } else {
            board.setMultipleTake(false);
        }
        if (!board.isMultipleTake()) {
            changeTurn();
        }
        board.setCurrent(end);
        possibleMoves.clear();
        notifyBoardObservers();
    }
}
