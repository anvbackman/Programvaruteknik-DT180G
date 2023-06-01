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

#### BaseStat
The abstract BaseStat class acts as the superclass of the three types of stats and will define behaviours that these
subclasses will use. It is responsible for retrieving the stats name and value, but also create static and dynamic 
modifiers that will be used later on.

We may start by creating said variables and then create a BaseStat object for these. Next up simply create a method
that returns each of these according to the project specifications. statName, baseValue and staticModifier will need no
explanation but the method getModifiedValue is simply created by getting the base value and adding the static and dynamic
modifiers to it. The getTotalModifier method is simular but only adds the static and dynamic modifiers together.

The class should also contain methods for adjusting the static and dynamic modifiers and this is done by creating it
with a parameter which then is appended to the original variable. 
```
public void adjustStaticModifier(int staticModifier) {
        this.staticModifier += staticModifier;
    }
```
The resetDynamicModifier is created in a simular way, but without a parameter and the value is instead set to 0.

Lastly the class will use a toString() method to return the stats name, modified value and total modifier.


#### Attribute, Trait and CombatStat
These classes all derive from the BaseStat class, but there are some differences in how they are implemented when it 
comes to the CombatStat class.


The CombatStat class adds two new functionalities. These are for attribute and trait reliance and is firstly set as
instance variables and then initialized in the constructor. Instead of returning the statName and baseValue to the
superclass it returns the statName and the baseValue as 0. 

## Discussion