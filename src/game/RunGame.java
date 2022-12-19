package game;

//singleton odpowiadajacy za uruchomienie tylko jednej gry w danym momencie
public class RunGame {
    private static RunGame instance;
    private final String value;
    private BoardGame game;

    private RunGame(String value) {
        this.value = value;
        try {
            if (value.equals("Checkers")) {
                GameCreator creator = new CheckersCreator();
                game = creator.crateGame();
                game.start();
            } else if (value.equals("Knights")) {
                GameCreator creator = new KnightsCreator();
                game = creator.crateGame();
                game.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BoardGame getGame() {
        return game;
    }

    //usuwanie instancji
    public static void clean() {
        instance = null;
    }

    public static RunGame getInstance(String value) {
        if (instance == null) {
            instance = new RunGame(value);
        }
        return instance;
    }
}
