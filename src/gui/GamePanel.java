package gui;

import javax.swing.*;

public class GamePanel extends JPanel {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

    public GamePanel(BoardPanel boardPanel, ControlPanel controlPanel) {
        splitPane.setLeftComponent(boardPanel);
        splitPane.setRightComponent(controlPanel);
        splitPane.setDividerLocation(640);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        add(splitPane);
    }
}
