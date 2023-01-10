package game;

import game.creators.GameCreator;

//singleton odpowiadajacy za uruchomienie tylko jednej gry w danym momencie
public class RunGame {
    private static volatile RunGame instance;
    private BoardGame game;

    private RunGame() {
    }

    public static RunGame getInstance() {
        if (instance == null) {
            synchronized (RunGame.class) {
                if (instance == null) {
                    instance = new RunGame();
                }
            }
        }
        return instance;
    }

    public void newGameWithCreator(GameCreator gameCreator) {
        game = gameCreator.createGame();
    }

    public BoardGame getGame() {
        if (game == null) {
            throw new IllegalStateException("Call newGameWithCreator() before getGame()");
        }
        return game;
    }
}
