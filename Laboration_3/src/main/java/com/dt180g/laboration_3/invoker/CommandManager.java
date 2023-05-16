package com.dt180g.laboration_3.invoker;


import com.dt180g.laboration_3.commands.CommandInterface;
import com.dt180g.laboration_3.commands.MoveCommand;
import com.dt180g.laboration_3.commands.NewGameCommand;
import com.dt180g.laboration_3.validation.InvalidMoveException;

import java.util.Deque;
import java.util.LinkedList;

/**
 * CommandManager class used for redo, undo and clearing the moves a player makes.
 * @author Andreas Backman
 */
public class CommandManager {
    // Creating the class INSTANCE
    public static final CommandManager INSTANCE = new CommandManager();
    // Deque for holding the undo and redo moves
    private Deque<MoveCommand> undoMoves;
    private Deque<MoveCommand> redoMoves;

    /**
     * Constructor for creating LinkedLists
     */
    private CommandManager() {
        redoMoves = new LinkedList<>();
        undoMoves = new LinkedList<>();
    }



    /**
     * Method to execute commands depending on if the command is an instance of the classes NewGameCommand
     * where the moves should be cleared or MoveCommand where push the elements to undoMoves and clear any redoMoves.
     * The method will catch any InvalidMoves.
     * @param command the command executed
     */
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

    /**
     * Method for undoing the latest move by popping the last made move, unexecuting the undone command and pushing it to
     * redoMoves so that the player can change its mind.
     * @throws InvalidMoveException if undoMoves is null
     */
    public void undoMove() {
        try {
            if (!undoMoves.isEmpty()) {
                MoveCommand undoneCommand = undoMoves.pop();
                undoneCommand.unExecute();
                redoMoves.push(undoneCommand);

            }
        }
        catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for redoing an undone move by popping the move from redoMoves, execute the redoneCommand and push
     * the command to undoMoves.
     * @throws InvalidMoveException if redoMoves is null
     */
    public void redoMove() {
        try {
            if (!redoMoves.isEmpty()) {
                MoveCommand redoneCommand = redoMoves.pop();
                redoneCommand.execute();
                undoMoves.push(redoneCommand);
            }
        }
        catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method for getting the amount of undoes
     * @return the size of undoMoves
     */
    public int getUndoAmount() {
        return undoMoves.size();
    }

    /**
     * Method for getting the amount of redoes
     * @return the size of redoMoves
     */
    public int getRedoAmount() {
        return redoMoves.size();
    }

    /**
     * Method for clearing all moves by clearing redoMoves and undoMoves
     */
    private void clearMoves() {
        redoMoves.clear();
        undoMoves.clear();
    }


}
