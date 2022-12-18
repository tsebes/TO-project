package gui;

import game.RunGame;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ControlPanel extends JPanel implements Observable {

    private final Set<Observer> observers = new HashSet<>();

    public ControlPanel() {
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
            RunGame.clean();
            notifyObservers();
        });
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }
}
