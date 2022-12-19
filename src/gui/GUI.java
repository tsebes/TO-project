package gui;

import javax.swing.*;

public class GUI extends JFrame implements Observer {

    private final BoardPanel boardPanel;
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final ControlPanel controlPanel;
    private final CheckersBoardPanel checkersBoardPanel;
    private final CheckersGamePanel checkersGamePanel;
    private final CheckersControlPanel checkersControlPanel;

    private String panel;

    public GUI() {
        menuPanel = new MenuPanel(this);
        boardPanel = new BoardPanel();
        checkersBoardPanel = new CheckersBoardPanel();
        controlPanel = new ControlPanel(this);
        checkersControlPanel = new CheckersControlPanel(this);
        gamePanel = new GamePanel(boardPanel, controlPanel);
        checkersGamePanel = new CheckersGamePanel(checkersBoardPanel, checkersControlPanel);

        menuPanel.addObserver(this);
        controlPanel.addObserver(this);
        checkersControlPanel.addObserver(this);
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
        //if (menuPanel.isVisible()) {
        if (panel.equals("Knights")) {
            menuPanel.setVisible(false);
            setContentPane(gamePanel);
            pack();
            gamePanel.setVisible(true);
        } else if (panel.equals("Checkers")) {
            menuPanel.setVisible(false);
            setContentPane(checkersGamePanel);
            pack();
            checkersGamePanel.setVisible(true);
        } else if (panel.equals("Menu")) {
            checkersGamePanel.setVisible(false);
            gamePanel.setVisible(false);
            setContentPane(menuPanel);
            pack();
            menuPanel.setVisible(true);
        }
    }

    public void setPanel(String panel){
        this.panel = panel;
    }

    public CheckersBoardPanel getCheckersBoardPanel() {
        return checkersBoardPanel;
    }

    @Override
    public void update() {
        changePanel();
    }
}
