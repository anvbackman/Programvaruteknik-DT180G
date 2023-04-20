# Laboration 1

---
## Environment & Tools

Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome,
Windows Powershell, Bitbucket

---
## Purpose
The aim of this lab is to enhance the cohesiveness and scalability of a Rock, Paper, Scissors game
implementation by applying the Strategy Design Pattern, while addressing the issues of low cohesion, tight
coupling, and potential violation of the game's core rules.

#### Concrete Goals:
* Refactor the current implementation to utilize the Strategy Design Pattern by introducing an abstract
  Tool class and deriving concrete tool classes from it
* Modify the Player constructor to correctly instantiate and use the new concrete tool classes.
* Refactor the Game::determineWinner(..) method to eliminate the use of switch / case statements
  based on tool names and to ensure the game's integrity by adhering to the core rules.
* Ensure that the final implementation adheres to the provided class diagram and specifications.

---
## Procedures

### First Step

The assignment began by looking at the updated class diagram and comparing it to the previous one.
One can then see that what's new is that the tools each has its own extended class. On the type of arrow used it can be 
seen that these new classes will inherit the Tool class. We can also see that both in the new Tool classes and 
the original Tool class, we will implement a way for the game to get each tools weakness.

Something one should notice is the symbol in front of the variables, constructors and methods. This is of importance
regarding if these should be set to public (+), private (-) or protected (#). This was one of the first bumps that was 
faced, which meant I had to read up on this.


### Tool

Since the Tool class will serve as an abstract base class, we may start by changing this right away.
According to the updated diagram, the constructor will need to be protected. Here one can simply change the constructor
from public to protected.

A method is then needed in order to get the weakness of each tool. This method is created as an abstract
String method and named getWeakness(). This then implements the classes ToolRock, ToolPaper and ToolScissor getWeakness()
methods which in turn overrides the method. The following shows the method inside of the ToolPaper class.
```
@Override public Tool getWeakness() {
    return new ToolScissors();
}
```


At this time the classes ToolRock, ToolPaper and ToolScissors have been created and will extend the Tool class
and the methods to override the Tool()::getWeakness() method.

In order to set the names of the tools we may create a constructor with its respective name. This is then sent to the
nameOfTool String inside the Tool class. This will be used to determine the weakness of each tool and also to update
the list of tool names.
Here is an example of setting the name of ToolPaper.
```
public ToolPaper() {
    super("Paper");
}
```


### Player

In order to resolve the issue with the Player constructor, the newly created ToolRock, ToolPaper and ToolScissors is used
to add the names of the tools to the array of tools instead of using String parameters. This is done by simply adding 
each class as follows
```
public Player(final String nameOfPlayer) {
    this.tools = new ArrayList<Tool>(
        Arrays.asList(new ToolRock(), new ToolPaper(), new ToolScissors()));
    this.playerName = nameOfPlayer;
}
```


### Game

The biggest risk with the old Game::determineWinner() was that the game would favour player1 if an error occurs,
that make the switch case fail. To get around this our getWeakness() method is used to compare this to the second
players tool name. Meaning if the name of the second players tool equals the weakness of the first persons tool, then
the second player would get a point. And if not, of course the first player would get a point.
```
if (secondPlayerTool.toString().equals(firstPlayerTool.getWeakness().toString())) {
    return player2;
}
else {
    return player1;
}
```

I faced some problems comparing these at first before adding the .toString() accessor to the statement.
As found out later, a better way would be to determine this based on class equality using the .getClass() 
accessor.


---
## Discussion

### Validation

In order to validate that the implementation works and that the purposes has been reached, the program has been
executed several times, both via the IDE and by creating a JAR-file and running. 

The Tool class has been refactored according to the Strategy Design Pattern, by making the Tool class into an abstract
class. By doing this the ToolRock, ToolPaper and ToolScissors classes could be extended.
By getting the tools names and weaknesses from ToolRock, ToolPaper and ToolScissors classes and use these instead of 
the previous String parameters, we can exclude the risk of new tools being added (without creating a new extended class 
and adding this class to the Tool list and also exclude spelling mistakes (without changing the expanded classes).

By refactoring the method determineWinner() by getting rid of the switch / case statements,
the risk of a player getting a point for the wrong reason is removed. Now there is only two possible options,
either player2 gets a point if the player2 tool is the same as player1s weakness, or player1 gets a point.
After this implementation has been made, we can see that the winner is determined when a player reaches the win limit 
of 3 and also the amount of matches needed before the game is finished is shown.

Lastly we can see that the new implementation matches what was expected from the updated class diagram.
By reaching the goals set, we may establish that the purpose of this assignment has been fulfilled.

### Personal Reflection

I have enjoyed this assignment. I think it was a good example for starting to learn how to think using classes. I feel
that the course literature matches what has been asked of me in this assignment.