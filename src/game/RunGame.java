package game;

import game.creators.GameCreator;

//singleton odpowiadajacy za uruchomienie tylko jednej gry w danym momencie
public class RunGame {
    private static RunGame instance;
    private BoardGame game;

    private RunGame() {
    }

    //usuwanie instancji
    public static void clean() {
        instance = null;
    }

    public static RunGame getInstance() {
        if (instance == null) {
            instance = new RunGame();
        }
        return instance;
    }

    public void newGameWithCreator(GameCreator gameCreator) {
        game = gameCreator.crateGame();
    }

    public BoardGame getGame() {
        return game;
    }
}
