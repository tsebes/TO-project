package game;

import java.util.Set;

public interface State {

    public abstract Set<Coordinates> getPossibleMoves(Coordinates piece);
    public abstract Set<Coordinates> getPossibleJumps(Coordinates piece);

}
