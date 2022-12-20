package game;

import game.commands.CommandHistory;
import game.commands.Move;
import game.enums.Turn;
import gui.BoardObserver;

import java.util.HashSet;
import java.util.Set;

public abstract class BoardGame implements Observable {

    protected final Set<BoardObserver> observers = new HashSet<>();
    protected final Set<Coordinates> possibleMoves = new HashSet<>();
    protected final CommandHistory commandHistory = new CommandHistory();
    protected final Board board;
    protected Turn currentTurn;

    public BoardGame(Board board) {
        this.board = board;
        currentTurn = Turn.WHITE;
    }

    protected void changeTurn() {
        currentTurn = (currentTurn == Turn.WHITE) ? Turn.BLACK : Turn.WHITE;
    }

    public abstract void updatePossibleMoves(Coordinates piece);

    public void move(Coordinates start, Coordinates end) {
        Move moveCommand = new Move(commandHistory, board, start, end);
        moveCommand.execute();
        changeTurn();
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

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public Set<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }
}
