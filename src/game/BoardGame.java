package game;

import game.commands.CommandHistory;
import game.commands.Move;
import game.enums.Player;
import gui.BoardObserver;

import java.util.HashSet;
import java.util.Set;

public abstract class BoardGame implements Observable {

    protected final Set<BoardObserver> observers = new HashSet<>();
    protected final Set<Coordinates> possibleMoves = new HashSet<>();
    protected final CommandHistory commandHistory = new CommandHistory();
    protected final Board board;
    protected Player currentTurn;

    public BoardGame(Board board) {
        this.board = board;
        currentTurn = Player.WHITE;
    }

    protected void changeTurn() {
        currentTurn = (currentTurn == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    public abstract void updatePossibleMoves(Coordinates piece);

    public abstract boolean canJump(Coordinates piece);

    public abstract boolean jumped(Coordinates start, Coordinates end);

    public abstract boolean isMovePossible(Coordinates piece);

    public abstract boolean isJumpPossible(Coordinates piece);

    public Set<Coordinates> getTaken(Coordinates start, Coordinates end) {
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
                }
            }
            if (end.x() > start.x() && end.y() < start.y()) {
                temp = new Coordinates(start.x() + i, start.y() - i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                }
            }
            if (end.x() < start.x() && end.y() > start.y()) {
                temp = new Coordinates(start.x() - i, start.y() + i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                }
            }
            if (end.x() < start.x() && end.y() < start.y()) {
                temp = new Coordinates(start.x() - i, start.y() - i);
                if (board.getField(temp).getPlayer() != currentTurn && !board.getField(temp).isEmpty()) {
                    takenPieces.add(temp);
                }
            }
        }
        return takenPieces;
    }

    public void move(Coordinates start, Coordinates end) {
        boolean jumpingMove = jumped(start, end);
        Move moveCommand = new Move(commandHistory, board, start, end, getTaken(start, end));
        moveCommand.execute();
        if (jumpingMove) {
            board.setMultipleTake(this.canJump(end));
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

    @Override
    public void addBoardObserver(BoardObserver boardObserver) {
        observers.add(boardObserver);
    }

    @Override
    public void removeBoardObserver(BoardObserver boardObserver) {
        observers.remove(boardObserver);
    }

    @Override
    public void notifyBoardObservers() {
        for (BoardObserver observer : observers) {
            observer.update();
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public Set<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }
}
