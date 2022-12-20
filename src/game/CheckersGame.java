package game;


public class CheckersGame extends BoardGame {

    public CheckersGame(Board board) {
        super(board);
        System.out.println("Created new Checkers game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        // TODO
        possibleMoves.add(new Coordinates(piece.x() - 1, piece.y() + 1));
        possibleMoves.add(new Coordinates(piece.x() - 1, piece.y() - 1));
        notifyBoardObservers();
    }

}
