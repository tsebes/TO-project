package gui;

import game.RunGame;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class MenuPanel extends JPanel implements Observable {

    private Set<Observer> observers = new HashSet<>();

        public MenuPanel() {
            setPreferredSize(new Dimension(540,440));
            setBackground(Color.GRAY);
            setLayout(null);
            addCheckersButton();
            addKnightsButton();
        }

        private void addCheckersButton(){
            JButton checkersButton = new JButton("Checkers");
            checkersButton.setBackground(new java.awt.Color(113, 167, 148));
            checkersButton.setBounds(60, 170, 100, 50);
            add(checkersButton);
            //akcja przycisków z wykorzystaniem singletonu
            checkersButton.addActionListener(e -> {
                RunGame.getInstance("Checkers");
                //zmień panel
                notifyObservers();
            });
        }

        private void addKnightsButton(){
            JButton knightsButton = new JButton("Knights");
            knightsButton.setBackground(new java.awt.Color(113, 167, 148));
            knightsButton.setBounds(220, 170, 100, 50);
            add(knightsButton);
            //akcja przycisków z wykorzystaniem singletonu
            knightsButton.addActionListener(e -> {
                RunGame.getInstance("Knights");
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
