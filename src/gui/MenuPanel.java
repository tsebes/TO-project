package gui;

import game.RunGame;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class MenuPanel extends JPanel implements Observable {

    private final Set<Observer> observers = new HashSet<>();
    private final GUI gui;

    public MenuPanel(GUI gui) {
        this.gui = gui;
        setPreferredSize(new Dimension(540, 440));
        setBackground(Color.GRAY);
        setLayout(null);
        addTitle();
        addCheckersButton();
        addKnightsButton();
    }

    private void addTitle() {
        JLabel title = new JLabel("Board Games Platform");
        title.setFont(new Font("Serif", Font.PLAIN, 40));
        title.setBounds(80, 120, 400, 50);
        add(title);
    }

    private void addCheckersButton() {
        JButton checkersButton = new JButton("Checkers");
        checkersButton.setBackground(new java.awt.Color(113, 167, 148));
        checkersButton.setBounds(100, 240, 100, 50);
        add(checkersButton);
        //akcja przycisków z wykorzystaniem singletonu
        checkersButton.addActionListener(e -> {
            RunGame.getInstance("Checkers");
            gui.setPanel("Checkers");
            gui.getCheckersBoardPanel().setGame(RunGame.getInstance("Checkers").getGame());
            //zmień panel
            notifyObservers();
        });
    }

    private void addKnightsButton() {
        JButton knightsButton = new JButton("Knights");
        knightsButton.setBackground(new java.awt.Color(113, 167, 148));
        knightsButton.setBounds(300, 240, 100, 50);
        add(knightsButton);
        //akcja przycisków z wykorzystaniem singletonu
        knightsButton.addActionListener(e -> {
            RunGame.getInstance("Knights");
            gui.setPanel("Knights");
            gui.getKnightsBoardPanel().setGame(RunGame.getInstance("Knights").getGame());
            //zmień panel
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