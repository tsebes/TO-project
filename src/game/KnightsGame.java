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
    @Override
    public boolean canJump(Coordinates piece) {
        //TODO
        return false;
    }

    @Override
    public boolean jumped(Coordinates start, Coordinates end) {
        return Math.abs(start.x() - end.x()) > 1 && Math.abs(start.y() - end.y()) > 1;
    }
}
