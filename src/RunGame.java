
//singleton odpowiadajacy za uruchomienie tylko jednej gry w danym momencie
public class RunGame {
    private static RunGame instance;
    private String value;

    private RunGame(String value) {
        this.value = value;
        try {
        if(value.equals("Checkers")){
            GameCreator creator = new CheckersCreator();
            BoardGame game = creator.crateGame();
            game.start();
        }
        else if(value.equals("Knights")){
            GameCreator creator = new KnightsCreator();
            BoardGame game = creator.crateGame();
            game.start();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
