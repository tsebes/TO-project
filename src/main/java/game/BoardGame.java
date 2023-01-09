package game;

import game.commands.Command;
import game.commands.CommandHistory;
import game.commands.Move;
import game.commands.Undo;
import game.enums.PieceType;
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
    protected Player winner;
    private PieceType takenType;

    public BoardGame(Board board) {
        this.board = board;
        currentTurn = Player.WHITE;
    }

    protected void changeTurn() {
        currentTurn = (currentTurn == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    public abstract void updatePossibleMoves(Coordinates piece);

    public abstract void move(Coordinates start, Coordinates end);

    protected abstract boolean canJump(Coordinates piece);

    protected abstract boolean jumped(Coordinates start, Coordinates end);

    public abstract boolean gameEnded();

    public void undo() {
        if (commandHistory.canUndo()) {
            Command undo = new Undo(commandHistory);
            undo.execute();
            changeTurn();
            possibleMoves.clear();
            notifyBoardObservers();
        }
    }

    public void redo() {
        if (commandHistory.canRedo()) {
            Command redoCommand = commandHistory.pop();
            redoCommand.undo();
            changeTurn();
            possibleMoves.clear();
            notifyBoardObservers();
        }
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

    public Player getWinner() {
        return winner;
    }
}
