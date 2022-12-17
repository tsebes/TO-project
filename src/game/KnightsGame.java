package game;

import gui.GUI;

public class KnightsGame implements BoardGame {
    @Override
    public void start() {
        System.out.println("Knights game - start");
        new GUI();
    }
}
