package com.dt180g.laboration_3.issuers;

import com.dt180g.laboration_3.commands.MoveCommand;
import com.dt180g.laboration_3.commands.NewGameCommand;
import com.dt180g.laboration_3.commands.ShowCommand;
import com.dt180g.laboration_3.invoker.CommandManager;
import com.dt180g.laboration_3.support.AppConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class Replayer {

    private BufferedReader bufferedReader;


    public Replayer() throws IOException, URISyntaxException {
        bufferedReader = new BufferedReader(new FileReader(AppConfig.getLogFilePath()));
    }

    public Replayer(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void runReplay() throws IOException {
        int numDiscs = Integer.parseInt(bufferedReader.readLine().trim());
        CommandManager.getInstance().executeCommand(new NewGameCommand(numDiscs));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            try {
                if (line.equals(AppConfig.LOG_UNDO_SYMBOL)) {
                    CommandManager.getInstance().undoMove();
                }
                else {
                    String[] moveData = line.split(",");
                    int sourceIndex = Integer.parseInt(moveData[0]);
                    int destIndex = Integer.parseInt(moveData[1]);
                    CommandManager.getInstance().executeCommand(new MoveCommand(sourceIndex, destIndex));
                }
                if (AppConfig.shouldShowReplayMoves()) {
                    CommandManager.getInstance().executeCommand(new ShowCommand());
                }
            }
            catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new IOException("Invalid log file format: " + e.getMessage());
            }
        }

    }

}
