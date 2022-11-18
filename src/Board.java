import java.awt.*;
import javax.swing.*;

public class Board {

    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private static final int TILE_SIZE = 50;

    public void show() {
        JFrame f = new JFrame("Board example");

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                JButton tile = new JButton();
                tile.setBounds(TILE_SIZE * i, TILE_SIZE * j, TILE_SIZE, TILE_SIZE);
                if ((i + j) % 2 == 0) {
                    tile.setBackground(Color.WHITE);
                } else {
                    tile.setBackground(Color.BLACK);
                }
                f.add(tile);
            }
        }

        f.setSize(TILE_SIZE * WIDTH + 20, TILE_SIZE * HEIGHT + 40);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}