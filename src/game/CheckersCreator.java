package game;

public class CheckersCreator implements GameCreator {
    @Override
    public BoardGame crateGame() {
        return new CheckersGame();
    }
}
