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

/**
 * Replayer class which reads and replays games from a log file.
 * @author Andreas Backman
 */
public class Replayer {
    // Variable for the BufferedReader
    private BufferedReader bufferedReader;

    /**
     * Constructor that reads data from the log file
     * @throws IOException if an I/O error occurs
     * @throws URISyntaxException if a URI syntax error occurs
     */
    public Replayer() throws IOException, URISyntaxException {
        bufferedReader = new BufferedReader(new FileReader(AppConfig.getLogFilePath()));
    }

    /**
     * Constructor to set this bufferedReader to existing bufferedReader
     * @param bufferedReader this bufferedReader
     */
    public Replayer(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    /**
     * Method for getting data from log file. Reading the number of discs and initializes a new game using that amount
     * of discs. It also reads the log file and executes the corresponding move and executes ShowCommand if needed.
     * @throws IOException if an I/O error occurs
     */
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
                    String[] moveData = line.split(" ");
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
