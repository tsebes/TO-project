package gui;

import javax.swing.*;

public class CheckersGamePanel extends JPanel {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

    public CheckersGamePanel(CheckersBoardPanel checkersBoardPanel, CheckersControlPanel checkersControlPanel) {
        splitPane.setLeftComponent(checkersBoardPanel);
        splitPane.setRightComponent(checkersControlPanel);
        splitPane.setDividerLocation(640);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        add(splitPane);
    }
}
