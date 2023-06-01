# Final Project

## Environments & Tools
Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome,
Windows Powershell, Bitbucket

## Purpose

The goal of this project is to implement the knowledge in OOP learnt in the course modules and 
course literature, by assuming the role of a dungeon master and implement solutions that will 
go hand in hand with pre-existing classes.
needed for the game to run as the project specification.
The project therefore aims to assess ones understanding of the different parts of OOP,
such as class diagrams and hierarchies, documentation using Javadoc, unit testing, 

* Making sure that the implementation follows the specifications of the project such as using the pre-existing
    constants.
* Implementing classes in an organized manner using appropriate inheritance and relationships between classes
* The intended behavior of each class need to be fulfilled
* All unit tests and mvn -test need to be passed without issues, ensuring that they produce an expected result
* Making sure that the implementations ensures readability and maintainability
* Provide clear explanations of the implementations using Javadoc, making sure that others may understand it
* Making sure that the file structure adheres with the specification in the project assignment
* Implementing a solution for creating the games characters and equipping them with gear, stats and abilities

## Procedures

### Stats

#### Stats Manager
According to the project assignment the StatsManager class is to be implemented as an eager Singleton in
order to give access to its single instance. Since the INSTANCE attribute is meant to be public there is no need for
one to create a method for returning it. Therefor this is straight forward.

The class is responsible for managing the stats attribute, trait and combat stats, so in order to store these,
a list is created for each stat.
These are then used in the constructor to assign each of them with their constants which is located in the AppConfig class.

The class consists of five methods responsible for returning the stats themselves but also a randomized version of
the attribute and trait stat. The regular returns are straight forward but for getting the randomized stats the
pre-existing class Randomizer and its method getRandomValue() is to be used. 
This is done by assigning the method containing the size of the list - 1, to a variable and then using that to get a 
random stat from the list. The reason for subtracting 1 from the size of the list is because the valid range
of the list is 0 to n-1 making sure that the random index that the getRandomValue method generates, falls within this
range and ensures that an IndexOutOfBoundsException doesn't occur.
```
public String getRandomAttributeName() {
        int randomAttribute = Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1);
        return attributeNames.get(randomAttribute);
    }
```

#### 

## Discussion