package game.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SaveCommands {
    private static volatile SaveCommands instance;
    public BufferedWriter writer;

    private SaveCommands() {
    }

    public static SaveCommands getInstance() {
        if (instance == null) {
            synchronized (SaveCommands.class) {
                if (instance == null) {
                    instance = new SaveCommands();
                }
            }
        }
        return instance;
    }

    public void saveHistory(Command command) {
        try {
            writer = new BufferedWriter(new FileWriter("src/main/java/game/commands/history.txt", true));
            writer.write(command.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearHistory() {
        try {
            writer = new BufferedWriter(new FileWriter("src/main/java/game/commands/history.txt", false));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

