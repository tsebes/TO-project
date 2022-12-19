package gui;

import javax.swing.*;

public class KnightsGamePanel extends JPanel {
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

    public KnightsGamePanel(KnightsBoardPanel knightsBoardPanel, KnightsControlPanel knightsControlPanel) {
        splitPane.setLeftComponent(knightsBoardPanel);
        splitPane.setRightComponent(knightsControlPanel);
        splitPane.setDividerLocation(640);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setEnabled(false);
        add(splitPane);
    }
}