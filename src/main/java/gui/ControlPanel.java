package gui;

import game.Board;
import game.BoardGame;
import game.RunGame;
import game.commands.SaveCommands;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel implements BoardObserver{

    private final GUIObserver guiObserver;
    private final BoardPanel boardPanel;
    private Board board;
    private BoardGame game;

    public ControlPanel(GUIObserver guiObserver, BoardPanel boardPanel) {
        this.guiObserver = guiObserver;
        this.boardPanel = boardPanel;
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
            boardPanel.game.undo();
        });
    }

    private void addRedoButton() {
        JButton redo = new JButton("Redo");
        redo.setBackground(new java.awt.Color(113, 167, 148));
        redo.setBounds(60, 250, 100, 50);
        add(redo);
        redo.addActionListener(e -> {
            boardPanel.game.redo();
        });
    }

    private void addLeaveButton() {
        JButton leave = new JButton("Leave");
        leave.setBackground(new java.awt.Color(113, 167, 148));
        leave.setBounds(60, 480, 100, 50);
        add(leave);
        leave.addActionListener(e -> {
            guiObserver.update(GUI.Panel.Menu);
            SaveCommands.getInstance().clearHistory();
        });
    }

    @Override
    public void update() {
        game.addBoardObserver(this);
    }
}