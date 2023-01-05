package gui;

import javax.swing.*;

public class GUI extends JFrame implements GUIObserver {

    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final BoardPanel boardPanel = new BoardPanel();

    public GUI() {
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(boardPanel, new ControlPanel(this, boardPanel));

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
        }
    }

    @Override
    public void update(Panel panel) {
        changePanel(panel);
    }

    public enum Panel {
        Menu,
        Game
    }
}
