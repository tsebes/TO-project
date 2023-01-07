package gui;

import game.BoardGame;
import game.RunGame;
import game.commands.SaveCommands;

import javax.swing.*;
import java.awt.*;

public class WinnerPanel  extends JPanel {
    private final GUIObserver guiObserver;
    private JLabel winnerMessage;
    private BoardGame game;

    public WinnerPanel(GUIObserver guiObserver) {
        this.guiObserver = guiObserver;
        setPreferredSize(new Dimension(540, 440));
        setBackground(Color.GRAY);
        setLayout(null);
        addMessage();
        addReturnToMenu();
    }

    private void addMessage() {
        winnerMessage = new JLabel("No winner yet");
        winnerMessage.setHorizontalAlignment(SwingConstants.CENTER);
        winnerMessage.setFont(new Font("Serif", Font.PLAIN, 40));
        winnerMessage.setBounds(80, 120, 400, 50);
        add(winnerMessage);
    }

    public void updateMessage(){
        game = RunGame.getInstance().getGame();
        winnerMessage.setText(game.getWinner() + " has won!!!");
    }

    private void addReturnToMenu() {
        JButton menuButton = new JButton("Menu");
        menuButton.setBackground(new java.awt.Color(113, 167, 148));
        menuButton.setBounds(220, 240, 100, 50);
        add(menuButton);
        menuButton.addActionListener(e -> {
            guiObserver.update(GUI.Panel.Menu);
            SaveCommands.getInstance().clearHistory();
        });
    }

}
