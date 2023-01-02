package gui;

import game.RunGame;
import game.creators.CheckersCreator;
import game.creators.KnightsCreator;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final GUIObserver guiObserver;

    public MenuPanel(GUIObserver guiObserver) {
        this.guiObserver = guiObserver;
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
            RunGame.getInstance().newGameWithCreator(new CheckersCreator());
            guiObserver.update(GUI.Panel.Game);
        });
    }

    private void addKnightsButton() {
        JButton knightsButton = new JButton("Knights");
        knightsButton.setBackground(new java.awt.Color(113, 167, 148));
        knightsButton.setBounds(300, 240, 100, 50);
        add(knightsButton);
        //akcja przycisków z wykorzystaniem singletonu
        knightsButton.addActionListener(e -> {
            RunGame.getInstance().newGameWithCreator(new KnightsCreator());
            guiObserver.update(GUI.Panel.Game);
        });
    }
}