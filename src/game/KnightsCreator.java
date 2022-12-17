package game;

public class KnightsCreator implements GameCreator {
    @Override
    public BoardGame crateGame() {
        return new KnightsGame();
    }
}
