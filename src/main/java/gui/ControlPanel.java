package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private final GUIObserver guiObserver;

    public ControlPanel(GUIObserver guiObserver) {
        this.guiObserver = guiObserver;
        setPreferredSize(new Dimension(200, 640));
        setBackground(Color.GRAY);
        setLayout(null);
        addUndoButton();
        addRedoButton();
        addLeaveButton();
    }

    private void addUndoButton() {
        JButton undo = new JButton("Undo");
        undo.setBackground(new java.awt.Color(113, 167, 148));
        undo.setBounds(60, 170, 100, 50);
        add(undo);
        undo.addActionListener(e -> {
            //TODO
        });
    }

    private void addRedoButton() {
        JButton redo = new JButton("Redo");
        redo.setBackground(new java.awt.Color(113, 167, 148));
        redo.setBounds(60, 250, 100, 50);
        add(redo);
        redo.addActionListener(e -> {
            //TODO
        });
    }

    private void addLeaveButton() {
        JButton leave = new JButton("Leave");
        leave.setBackground(new java.awt.Color(113, 167, 148));
        leave.setBounds(60, 480, 100, 50);
        add(leave);
        leave.addActionListener(e -> {
            guiObserver.update(GUI.Panel.Menu);
        });
    }
}