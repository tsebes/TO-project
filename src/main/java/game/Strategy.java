package game;

import java.util.Set;

public interface Strategy {

    Set<Coordinates> getPossibleMoves(Board board, Coordinates piece);

    Set<Coordinates> getPossibleJumps(Board board, Coordinates piece);

}
