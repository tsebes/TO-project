package game;

public class KnightsGame extends BoardGame {

    public KnightsGame(Board board) {
        super(board);
        System.out.println("Created new Knights game");
    }

    @Override
    public void updatePossibleMoves(Coordinates piece) {
        possibleMoves.clear();
        // TODO
        possibleMoves.add(new Coordinates(piece.x() - 1, piece.y()));
        notifyBoardObservers();
    }

}
