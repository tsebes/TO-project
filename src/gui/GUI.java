package gui;

import javax.swing.*;

public class GUI extends JFrame implements Observer {

    private final MenuPanel menuPanel;
    private final KnightsBoardPanel knightsBoardPanel;
    private final KnightsGamePanel knightsGamePanel;
    private final KnightsControlPanel knightsControlPanel;
    private final CheckersBoardPanel checkersBoardPanel;
    private final CheckersGamePanel checkersGamePanel;
    private final CheckersControlPanel checkersControlPanel;

    private String panel;

    public GUI() {
        menuPanel = new MenuPanel(this);
        knightsBoardPanel = new KnightsBoardPanel();
        checkersBoardPanel = new CheckersBoardPanel();
        knightsControlPanel = new KnightsControlPanel(this);
        checkersControlPanel = new CheckersControlPanel(this);
        knightsGamePanel = new KnightsGamePanel(knightsBoardPanel, knightsControlPanel);
        checkersGamePanel = new CheckersGamePanel(checkersBoardPanel, checkersControlPanel);

        menuPanel.addObserver(this);
        knightsControlPanel.addObserver(this);
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
        if (panel.equals("Knights")) {
            menuPanel.setVisible(false);
            setContentPane(knightsGamePanel);
            pack();
            knightsGamePanel.setVisible(true);
        } else if (panel.equals("Checkers")) {
            menuPanel.setVisible(false);
            setContentPane(checkersGamePanel);
            pack();
            checkersGamePanel.setVisible(true);
        } else if (panel.equals("Menu")) {
            checkersGamePanel.setVisible(false);
            knightsGamePanel.setVisible(false);
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

    public KnightsBoardPanel getKnightsBoardPanel() {
        return knightsBoardPanel;
    }

    @Override
    public void update() {
        changePanel();
    }
}
