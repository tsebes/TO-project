package gui;

import javax.swing.*;
import game.RunGame;

public class Platform extends JFrame {
    public void showPlatform() {
        //tworzenie Jframe
        JFrame f = new JFrame("Platform example");
        f.getContentPane().setBackground(new java.awt.Color(89, 82, 76));
        f.setSize(420, 440);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Tekst tytułowy
        JLabel title = new JLabel("Board Game Platform");
        title.setBounds(65, 50, 300, 60);
        title.setFont(new java.awt.Font("Arial", 3, 25));
        f.add(title);

        //tekst wybierz grę
        JLabel l = new JLabel("Choose the game:");
        l.setBounds(50, 120, 200, 40);
        l.setFont(new java.awt.Font("Arial", 1, 15));
        f.add(l);

        //przyciski wyboru gry
        JButton checkersButton = new JButton("Checkers");
        JButton knightsButton = new JButton("Knights");
        checkersButton.setBackground(new java.awt.Color(113, 167, 148));
        knightsButton.setBackground(new java.awt.Color(113, 167, 148));
        checkersButton.setBounds(60, 170, 100, 50);
        knightsButton.setBounds(220, 170, 100, 50);
        f.add(checkersButton);
        f.add(knightsButton);

        //akcja przycisków z wykorzystaniem singletonu
        checkersButton.addActionListener(e -> {
          RunGame.getInstance("Checkers");
        });

        knightsButton.addActionListener(e -> {
            RunGame.getInstance("Knights");
        });

        //roboczo umiecilem przycisk leave na panelu głownym ale docelowo ma byc na panelu gry
        //przycisk ten definitywnie konczy gre poprzez usuniecie instancji
        //i umozliwia zaczecie kolejnej gry od nowa
        JButton leaveButton = new JButton("Leave");
        leaveButton.setBackground(new java.awt.Color(113, 167, 148));
        leaveButton.setBounds(150, 280, 100, 50);
        f.add(leaveButton);
        leaveButton.addActionListener(e -> {
            RunGame.clean();
        });

    }
}
