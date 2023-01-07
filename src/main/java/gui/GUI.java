package gui;

import javax.swing.*;

public class GUI extends JFrame implements GUIObserver {

    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final WinnerPanel winnerPanel;

    public GUI() {
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(new BoardPanel(this), new ControlPanel(this));
        winnerPanel = new WinnerPanel(this);

        configureFrame();
        setVisible(true);
    }

    private void configureFrame() {
        setTitle("Board Game Platform");
        add(menuPanel);
        pack();
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void changePanel(Panel panel) {
        switch (panel) {
            case Menu -> {
                winnerPanel.setVisible(false);
                gamePanel.setVisible(false);
                setContentPane(menuPanel);
                pack();
                menuPanel.setVisible(true);
            }
            case Game -> {
                menuPanel.setVisible(false);
                setContentPane(gamePanel);
                pack();
                gamePanel.setVisible(true);
                gamePanel.updateBoard();
            }
            case Winner -> {
                gamePanel.setVisible(false);
                setContentPane(winnerPanel);
                pack();
                winnerPanel.setVisible(true);
                winnerPanel.updateMessage();
            }
        }
    }

    @Override
    public void update(Panel panel) {
        changePanel(panel);
    }

    public enum Panel {
        Menu,
        Game,
        Winner
    }
}
