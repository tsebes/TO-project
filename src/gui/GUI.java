package gui;

import javax.swing.*;

public class GUI extends JFrame implements Observer {

    private final BoardPanel boardPanel;
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final ControlPanel controlPanel;

    public GUI() {
        boardPanel = new BoardPanel();
        menuPanel = new MenuPanel();
        controlPanel = new ControlPanel();
        gamePanel = new GamePanel(boardPanel, controlPanel);
        menuPanel.addObserver(this);
        controlPanel.addObserver(this);
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

    public void changePanel() {
        if (menuPanel.isVisible()) {
            menuPanel.setVisible(false);
            setContentPane(gamePanel);
            pack();
            gamePanel.setVisible(true);
        } else {
            gamePanel.setVisible(false);
            setContentPane(menuPanel);
            pack();
            menuPanel.setVisible(true);
        }
    }

    @Override
    public void update() {
        changePanel();
    }
}
