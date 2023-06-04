# Laboration 3

## Environments & Tools
Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome, Windows Powershell, Bitbucket

## Purpose
The aim of this lab is to implement a text-based version of the Tower of Hanoi game, while applying key
software engineering concepts such as the Command Design Pattern, Singleton pattern, and custom
logging for recording move history. The finished product needs to support undo and redo mechanics, as well
as offer replay functionality.

#### Concrete Goals:

* Implement the HanoiLogger class as a Lazy Singleton to log move activities during the game,
  adhering to lazy initialization and utilizing a customized formatter for log contents.
* Develop the MoveCommand and NewGameCommand classes as concrete commands implementing the
  CommandInterface, ensuring proper interaction with the HanoiEngine.
* Implement the CommandManager class as an Eager Singleton to handle command invocations,
  manage undo / redo mechanics, and maintain the state of executed, undone, and redoable
  commands.
* Create the Replayer class for replaying previous games using recorded log files, leveraging the
  CommandManager for command invocations and handling output silencing.
* Ensuring that the final implementation is in line with the design specifications, passes all provided
  unit tests, and demonstrates proficiency in applying relevant software engineering concepts.


## Procedures

### HanoiLogger

The HanoiLogger class is responsible for logging to the console during the Tower of Hanoi simulation. 
The class is implemented as a lazy singleton and is initialized when the getInstance() method is called.
To do this we will first make sure that the instance object in the instance field is declared private.
We may also declare a new Logger object right away.
The constructor will check if logging should be used with the help of the method shouldUseLog() which is located in
the AppConfig class.

It the log should be used the constructor will initialize the logger, otherwise the logger is set to null.
The getInstance() method will check if the instance variable is null, and if it is it will initialize a new HanoiLogger
object. 
If the logger is null it will check if the logger should be used and then initialize the logger.
```
public static HanoiLogger getInstance() {
        if (instance == null) {
            instance = new HanoiLogger();
        }
        else if (instance.logger == null) {
            if (AppConfig.shouldUseLog()) {
                instance.initializeLogger();
            }
        }
        return instance;
    }
```

The initializeLogger() method is what we use to get the logger, add the FileHandler and set its level, and add a formatter.
Make sure that the setUseParentHandlers method is set to false to avoid showing the logInfo INFO message to the console.

In order to log a message we declare the logInfo() method with a parameter to take a String argument.
This is what will be written to the console.
Here we make sure to only log if the shouldUseLog() method says so, and only if the logger is not null.

The class also need a method to close and reset the logger. The closeLogger() method creates a Handler object that iterates
over the getHandlers() method, and for each iteration calls the close() method and then removes the handler using the
removeHandler() method.
The resetLogger() method checks if the log should be used and if that's the case it first calls the closeLogger() method
to then call the initializeLogger() method.


### MoveCommand & NewGameCommand

The MoveCommand and NewGameCommand both implements the CommandInterface interface, which has the task of executing 
commands. Let us first take a look at the NewGameCommand which is responsible for setting the amount of discs to use,
and also reset the HanoiLogger when a new game starts. 
In order to keep track of the disc amount we declare a private instance variable that takes an integer.
This is then initialized during construction of a NewGameCommand object.

The method execute() is what will override the CommandInterface. The execute() method simply use a statement
to make sure that the amount of discs the user enters, is within the limits of the game. For this we use the constants
residing in the AppConfig class (DISC_AMOUNT_MINIMUM, and DISC_AMOUNT_MAXIMUM).
We then call the resetGame() method in the HanoiEngine class, using the disc amount as an argument, resets the logger
and log the disc amount as a String.

The MoveCommand class is used to move the discs from one tower to another. We may first declare two int variables
for this in the instance field.
These are then initialized in the constructor during construction.

The MoveCommand class got an execute() method as well, but this will instead execute the moves. This is done
using the performMove() method in the HanoiEngine class, which takes three arguments. The move from, move to
and a boolean set to true to update the move counter.
We then log the move using the logInfo() method in the HanoiLogger class.
```
@Override
    public void execute() {
        HanoiEngine.INSTANCE.performMove(fromTower, toTower, true);
        HanoiLogger.getInstance().logInfo(fromTower + " " + toTower);
    }
```
The MoveCommand class also makes it possible to undo a move by simply reversing the order of the execute arguments
and the boolean is set to false.
The method will then log an undo symbol which uses the constant LOG_UNDO_SYMBOL located in  the AppConfig class.


### CommandManager

The CommandManager class is used to redo, undo and clearing the moves made.
The class is to be implemented as an eager singleton, with the CommandManager INSTANCE object declared as public in
the instance field.
In order to store our redo and undo moves we create two MoveCommand Deques as well.
These are then initialized as LinkedLists during construction.

In order to execute our commands we utilize the executeCommand() method with a CommandInterface object as a parameter.
We then check if the move is valid using try / catch. If the move is valid we execute said command, and if the command
is a NewGameCommand, we clear the moves done. If it instead is a MoveCommand command we push the methods argument
to the undoMoves Deque and calls the clear() method on the redoMoves Deque.
If we catch an InvalidMoveException we throw an error message.


In order to undo and redo moves, we do create the undoMove() and redoMove() methods. Using try / catch we first check if
the Deque is not empty. If it is not empty, we create a new MoveCommand object and pops the move from the undoMoves/
redoMoves Deque. In the undoMoves() method we call the unExecute() method to make the move in the reversed order and
in the redoMove() method we instead call the execute() method to reverse the redoMove. Ones this is done we push the 
object to the Deque.
If we catch an InvalidMoveException we simply print out the error message.
```
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
```

We then want to return the Deque sizes using the getUndoAmount() and getRedoAmount() methods, which is straight forward.
Lastly we can use the clearMoves() method if we would like to clear the Deques, by simply calling the clear() method
on them.

## Discussion