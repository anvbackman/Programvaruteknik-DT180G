# Laboration 1

---
## Environment & Tools

Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome, 
Windows Powershell, Bitbucket

---
## Purpose
The goal for this assignment is to create implementations for an existing application of the game 
Rock, Paper, Scissors. The game "works" but has its flaws. In order to fulfill the assignment the following 
flaws need to be solved.
* Via the Player constructor, find a better solution to populate the Tool list, than adding the Rock, Paper, Scissors 
as parameters when constructing the Tool array.
* Implement a better solution to the determineWinner method, since the current method may become faulty if other errors
occur.



---
## Procedures

### Starting

The assignment began by looking at the updated class diagram and comparing it to the previous one.
One can then see that what's new is that the tools each has its own class. On the type of arrow used it can be seen
that these new classes will inherit the Tool class. We can also see that both in the new Tool classes and the original
Tool class, we will implement a way for the game to get each tools weakness.


### Tool

Since the Tool class will serve as an abstract base class, we may start by changing this right away.
We then know that a method is needed in order to get the weakness of each tool. This method is created as an abstract
String method.

The classes ToolRock, ToolPaper and RockScissors are then created and will extend the Tool class. 
In order to set the names of the tools we may create a constructor with its respective name. This is then sent to the 
nameOfTool String inside the Tool class.
To store the weakness of each tool, the method getWeakness is created where the tools weakness is stored as a String.


### Player

In the Player class the problem was that the name of the tools was set by adding the names as parameters to the array.
Since the names now can be collected from the classes ToolRock, ToolPaper and ToolScissors via the Tool class.

### Game
The biggest risk with the old Game::determineWinner was that the game would favour player1 if an error occurs,
that make the switch case fail. To get around this we may now use our getWeakness method to compare this to the second
players tool name. Meaning if the name of the second players tool equals the weakness of the first persons tool, then 
the second player would get a point. And if not, of course the first player would get a point.
By doing this we may exclude the possibility of a player getting a point because of the switch cases default being used.

---
## Discussion

### Validation

In order to validate that the implementation works and that the purposes has been reached, the program has been
executed several times, both via the IDE and by creating a JAR-file and running. The winner is randomized and also the 
amount of matches needed before the game is finished.
By getting the tools names and weaknesses from ToolRock, ToolPaper and ToolScissors classes we can both exclude the risk
of new tools being added and also exclude the risk of a player getting a point for the wrong reason.

By reaching the goals set, we may establish that the purpose of this assignment has been fulfilled.

### Personal Reflection