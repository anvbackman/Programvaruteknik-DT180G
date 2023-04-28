package com.dt180g.laboration_3.invoker;


import com.dt180g.laboration_3.commands.CommandInterface;
import com.dt180g.laboration_3.commands.MoveCommand;
import com.dt180g.laboration_3.commands.NewGameCommand;
import com.dt180g.laboration_3.validation.InvalidMoveException;

import java.util.Deque;
import java.util.LinkedList;

public class CommandManager {


    public static CommandManager INSTANCE = new CommandManager();
    private Deque<MoveCommand> undoMoves;
    private Deque<MoveCommand> redoMoves;

    private CommandManager() {
        redoMoves = new LinkedList<>();
        undoMoves = new LinkedList<>();
    }

    public static CommandManager getInstance() {
        return INSTANCE;
    }

    public void executeCommand(CommandInterface command) {
        try {
            command.execute();

            if (command instanceof NewGameCommand) {
                clearMoves();
            }
            else if (command instanceof MoveCommand) {
                undoMoves.push((MoveCommand) command);
                redoMoves.clear();
            }
        }

        catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    public void undoMove() {
        if (undoMoves != null) {
            MoveCommand undoneCommand = undoMoves.pop();
            undoneCommand.unExecute();
            redoMoves.push(undoneCommand);

        }
    }

    public void redoMove() {
        if (redoMoves != null) {
            MoveCommand redoneCommand = redoMoves.pop();
            redoneCommand.execute();
            undoMoves.push(redoneCommand);
        }

    }

    public int getUndoAmount() {
        return undoMoves.size();
    }

    public int getRedoAmount() {
        return redoMoves.size();
    }



    private void clearMoves() {
        redoMoves.clear();
        undoMoves.clear();
    }


}
