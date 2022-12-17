package gui;

import javax.swing.*;

public class GUI extends JFrame {

    private final BoardPanel boardPanel;

    public GUI() {
        boardPanel = new BoardPanel();
        configureFrame();
        setVisible(true);
    }

    private void configureFrame() {
        setTitle("Board example");
        add(boardPanel);
        pack();
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
