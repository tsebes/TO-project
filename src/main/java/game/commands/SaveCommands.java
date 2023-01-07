package game.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommands {
    private static final String PATH = "src/main/java/game/commands/history.txt";
    private static volatile SaveCommands instance;
    private BufferedWriter writer;

    private SaveCommands() {
        clearHistory();
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

    public void saveHistory(String message) {
        try {
            writer = new BufferedWriter(new FileWriter(PATH, true));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearHistory() {
        try {
            writer = new BufferedWriter(new FileWriter(PATH, false));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

