public class Main {
    public static void main(String[] args) {
        GameCreator creator = new CheckersCreator();
        BoardGame game = creator.crateGame();
        game.start();

        creator = new KnightsCreator();
        game = creator.crateGame();
        game.start();
    }
}
