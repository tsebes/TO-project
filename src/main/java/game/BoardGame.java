package game;

import game.commands.Command;
import game.commands.CommandHistory;
import game.commands.Undo;
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

    public BoardGame(Board board) {
        this.board = board;
        currentTurn = Player.WHITE;
    }

    public void changeTurn() {
        currentTurn = (currentTurn == Player.WHITE) ? Player.BLACK : Player.WHITE;
    }

    public abstract void updatePossibleMoves(Coordinates piece);

    public abstract void move(Coordinates start, Coordinates end);

    public abstract boolean canJump(Coordinates piece);

    public abstract boolean gameEnded();

    public void undo() {
        if (commandHistory.canUndo()) {
            Command undo = new Undo(commandHistory);
            undo.execute();
            possibleMoves.clear();
            notifyBoardObservers();
        }
    }

    public void redo() {
        if (commandHistory.canRedo()) {
            Command redoCommand = commandHistory.pop();
            redoCommand.undo();
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

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Set<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }

    public Player getWinner() {
        return winner;
    }
}
