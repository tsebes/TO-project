package gui;

import javax.swing.*;

public class GamePanel extends JPanel {

    private final BoardPanel boardPanel;

    public GamePanel(BoardPanel boardPanel, ControlPanel controlPanel) {
        this.boardPanel = boardPanel;
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(boardPanel);
        splitPane.setRightComponent(controlPanel);
        splitPane.setDividerLocation(640);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        add(splitPane);
    }

    public void updateBoard() {
        boardPanel.updateBoard();
    }
}
