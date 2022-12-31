package game;

import java.util.Set;

public interface State {

    Set<Coordinates> getPossibleMoves(Coordinates piece);

    Set<Coordinates> getPossibleJumps(Coordinates piece);

}
