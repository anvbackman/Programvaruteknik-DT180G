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

We may start by creating said variables and then construct a BaseStat object for these. Next up simply create a method
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
The resetDynamicModifier is created in a similar fashion, but without a parameter and the value is instead set to 0.
Lastly the class will use a toString() method to return the stats name, modified value and total modifier
which will show on the Hero details screen under the statistics.


#### Attribute, Trait and CombatStat
These classes all derive from the BaseStat class. The Attribute and Trait classes are implemented in the same way,
but there are some differences in how they are implemented when it comes to the CombatStat class.

The Attribute and Trait classes simply creates an object of themselves with parameters for the stat name and value,
and use these to refer to the superclass.
```
public Trait(String statName, int baseValue) {
        super(statName, baseValue);
    }
```

The CombatStat class adds two new functionalities. These are for attribute and trait reliance and is firstly declared as
instance variables and then initialized in the constructor. Instead of returning the statName and baseValue to the
superclass it returns the statName and the baseValue as 0. 
It then overrides the getBaseValue() method in BaseStat using the specified multiplication. 
```
@Override
    public int getBaseValue() {
        return (int) Math.round(attributeReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER +
                traitReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER);
    }
```

### Gear

#### GearManager
The GearManager class is responsible for manage the weapons and armor of the game and also provide the ability to 
generate random gear for the characters.

Similar to the StatsManager class, the GearManager class implement an eager singleton in order to give global access
to its instance. Since the class will need a way to store weapons and armor we first need somewhere to store them.
To do that one can simply declare these as Maps with the types String for storing the key values and List for storing
the values.

In the constructor, we initialize two Maps by assigning them the corresponding methods for retrieving weapons and armor. 
We set the Maps using the methods getAllMappedArmorPieces() and getAllMappedWeapons() which are implemented in the same way.
For the purpose of this explanation, let us focus on the details of getAllMappedArmorPieces().

To begin with, we create an empty HashMap and assign it to the variable armorPieces. The armor data is stored in a 
JSON file, so our first step is to retrieve this data and assign it to a list. 

Next, we iterate over each element in the armorList.
During each iteration, we create an Armor object by passing the current armorMap to its constructor. 
This gives access to the type of armor using the method getType() in the Armor class.
To obtain the values, we create a new list, using the armorType as the key, and initialize it as an empty ArrayList. 
We then add the current armor object to this list.

Finally, we add the armorType and the list of armor objects as arguments to the armorPieces HashMap using the put method.
Once all the iterations are complete, we return the armorPieces HashMap, which contains the armor mapped by their respective types.
```
public Map<String, List<Armor>> getAllMappedArmorPieces() {

        armorPieces = new HashMap<>();
        List<Map<String, String>> armorList = IOHelper.readFromFile("gear_armor.json");

        for (Map<String, String> armorMap : armorList) {
            Armor armor = new Armor(armorMap);
            String armorType = armor.getType();
            List<Armor> armorMappedList = armorPieces.getOrDefault(armorType, new ArrayList<>());
            armorMappedList.add(armor);
            armorPieces.put(armorType, armorMappedList);
        }

        return armorPieces;
    }
```

Next we will may implement the getWeaponsOfType() method which simply returns a weapon using the type.
The methods getRandomWeapon(Class<?>), getRandomOneHandedWeapon(Class<?>) and getAllArmorForRestriction(Class<?>)
are similar but with some exceptions in the if-statement used and the returns.
The most fundemental of these is the method getAllArmorForRestriction(Class<?>).

We start by initializing a new ArrayList called armorForClass and then iterate over the values of the armorPieces Map. 
For each iteration we iterate over the previous iteration using an Armor object, and for each of these iterations we 
check if the checkClassRestriction method in the Armor class allows this armor and if it does we add it to the armorForClass
list. After all iterations are completed we return the armorForClass list.
In the getRandomWeapon(Class<?>) we do the same but when we return the weapon we use the Randomizer class to give the
character a random weapon. The same goes for the getRandomOneHandedWeapon(Class<?>) method but here we check the class
restrictions AND that the weapon is not two-handed. This is done using a method inside the Weapon class.
```
public List<Armor> getAllArmorForRestriction(Class<?> armorForRestriction) {

        List<Armor> armorForClass = new ArrayList<>();

        for (List<Armor> armorList : armorPieces.values()) {
            for (Armor armor : armorList) {
                if (armor.checkClassRestriction(armorForRestriction)) {
                    armorForClass.add(armor);
                }
            }
        }

        return armorForClass;
    }
```

When we have the random weapons we need to be able to return these based on its type as well. And to do this we utilize
the methods getRandomOneHandedWeapon(List<String>) and getRandomWeapon(List<String>). These are implemented in the same
way as the previous methods with the exception that we instead of checking for class restrictions, we check if 
our parameter contains the type we get from Weapon:getType()
```
if (weaponType.contains(weapon.getType()) && !weapon.isTwoHanded()) {
                    aRandomWeapon.add(weapon);
                }
```

Lastly we also need to be able to return a random armor based on its type. This is done in a similar as
getAllArmorForRestriction(Class<?>) but with the use of two parameters. One for the type and one for the restriction.
The difference here is that we both check for the class restriction and the type and then returns it using the Randomizer
class to get a random weapon of the given type.
```
if (armor.checkClassRestriction(armorForRestriction) && armorType.contains(armor.getType())) {
                    armorForClass.add(armor);
                }
            }
        }

        return armorForClass.get(Randomizer.INSTANCE.getRandomValue(0, armorForClass.size() - 1));
```

#### BaseGear
The abstract class BaseGear acts as the superclass of the Armor and Weapon classes and will define behaviours that these
subclasses will use. It is responsible for providing information regarding the gears type, name and class restrictions.
So we may firstly declare these variables in the instance field.

The constructor will create a BaseGear object using said parameters. This is straight forward for the type and 
gear name, but classRestriction will need to be initialized as a new List. It then splits the class restrictions into
individual class names with the use of the split() method and then uses the trim method() to remove any whitespace.
We then retrieve the package name from the BaseHero class which retrieve the package name where this class is located.
In doing so we may then check that the class is found and then use that information to check if the class 
are allowed to use the gear or not.
```
this.classRestrictions = new ArrayList<>();

        for (String restriction : classRestrictions.split(",")) {
            String className = BaseHero.class.getPackageName() + "." + restriction.trim();
            try {
                Class<?> characterClass = Class.forName(className);
                this.classRestrictions.add(characterClass);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
```

The returns of type and class restrictions are returned using a regular method, but the gearName will be returned using 
the toString() method. 
To be able to check for class restrictions we simply create a boolean method to check if classRestrictions contains 
the class to be checked.
Lastly we will need to retrieve the stats that are connected to the gear and we may do so by declaring an abstract method,
that will retrieve these stats from the Armor and Weapon class.

#### Armor, Weapon
The Armor and Weapon class extends the BaseGear class and will add additional properties specific to the gear.
We know that the BaseGear is expecting its subclasses to refer the type, name and restrictions of the gear, and
this is simply done via a super in the constructor.

But these classes will also return some other things. The Weapon class will return the weapons damage, wield and attribute
and the Armor class will return the armor protection, the material of the armor and trait. 
The attribute and trait need to be returned randomized but are done in the same fashion.
In the constructor we may simply assign a new Attribute or Trait object and with the help of 
StatsManager:getRandomAttributeName() and getRandomTraitName() we may retrieve its name. The value of the stat
is also randomized but using the Randomizer class. 
```
public Weapon(Map<String, String> weapon) {
        super(weapon.get("type"), weapon.get("name"), (weapon.get("restriction")));

        damage = Integer.parseInt(weapon.get("damage"));
        wield = weapon.get("wield");
        attribute = new Attribute(StatsManager.INSTANCE.getRandomAttributeName(),
                Randomizer.INSTANCE.getRandomValue(0, AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND));
    }
```

Returning these variables are straight forward but we need to make sure that the getStat() method uses the @Override
annotation since it overrides its abstract method in the BaseGear class. The same goes for the toString() method
which will return the gears name and stat name.
There is a key difference in the Weapons class though. Since some weapons are two-handed and some are one-handed, we
need a way to check this. Using a boolean method we may return true if the variable wield contains the String "Two Handed".


### Abilities

#### BaseAbility

The BaseAbility class is an abstract class that acts as the base of the ability hierarchy. The class represents
all nine abilities of the game and provides functionality and methods to calculate and retrieve the cost of action
points and energy. All deriving subclasses make use of the BaseAbility's abstract methods.

With that said one may start by declaring the instance variables of the class, which is used for the cost of action
points and energy which will be initialized in the constructor when creating a BaseAbility object. These variables
are then returned using their respective get method.

To perform these ability we create the protected boolean method performAbility() which takes four parameters.
These consists of a String to get the ability name, an integer to get the amount of targets, another int for the 
damage or heal amount (the variable is called damage in this case) and a boolean to check if an enemy or hero should be targeted.
This will then be formatted according to the specification in the project assignment. 
The method will also specify what happens if a heal is executed by simply subtracting the damage variable from itself.
The method then returns a new AbilityInfo object to the GameEngine:characterAttack() method.
```
protected boolean performAbility(String info, int amountOfTargets, int damage, boolean targetEnemies) {
        String ability = String.format("%s", info + " (-" + actionPointCost + " AP, " + "-" + energyCost + " Energy)");
        if (isHeal()) {
            damage = - damage;
        }
        return GameEngine.INSTANCE.characterAttack(new AbilityInfo(ability, amountOfTargets, damage, targetEnemies, isMagic(), isHeal()));
    }
```

The BaseAbility class utilizes five abstract methods to retrieve information of each ability. As these methods are
abstract we simply do not add a body to it. These will be explained more thoroughly explained below.


#### The abilities

Each of the games abilities implements its own class and they all derive from the BaseAbility class. 
The abilities are implemented very similar but has some key differences, so let us start of with the most basic
ability which is the WeaponAttack class. This is an ability that is added to all characters in the game.

Same as all other abilities, the WeaponAttack class uses its constructor to refer to its superclass, telling it
what the ability's action point and energy cost is. In this case the cost of action points are set to 
the constant LOWEST_AP_COST which is located in the AppConfig class. The WeaponAttack class however does not use up
any energy, like every other ability does, which is why this value is set to 0. If it were to use energy it would
be declared in the same way as the ability point cost using a constant from the AppConfig class. For example
HIGH_ENERGY_COST.

Moving on to the abstract methods that the abilities will override. First of we need to check if the ability is
a magical ability, healing ability or physical ability. This is done using the isMagic() and isHeal() methods 
by simply returning true or false for each ability.

Then we use the getAmountOfTargets() method to return how many target the ability will attack or heal. This is done
using two constants residing in the AppConfig class. These are called ABILITY_SINGLE_TARGET and ABILITY_GROUP_TARGET
and is applied to each ability based on the project specification.

The boolean method execute() is used to return the abilities name, attack or heal value, amount of targets and who to
attack. The name of the ability is retrieved using the classes toString() method. It is important to note that all
single attack abilities shall be multiplied with the constant SINGLE_TARGET_ABILITY_MULTIPLIER to make them more
viable.

This was the most basic ability, so lets move on to one of the more advanced ones.
For example the ElementalBlast class.
This class utilizes two extra functions, which is a magical phrase which each of the magical and healing abilities uses
and the element variable which is only utilized by this class and the ElementalBolt class.
These are declared as instance variables and are initialized in the constructor and added to the toString() method,
which will be returned when execute is called.
```
public ElementalBlast(String element) {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.element = element;
        magicalPhrase = AppConfig.MAGICAL_PHRASE_1;
    }
```


### Characters

#### BaseCharacter

The abstract class BaseCharacter represents the base characters in the game and provides methods and functionality
for the stats, equipment and abilities that are utilized by the characters.
So to start of we may create instance variables for these three functionalities.
The variable for the stats and equipment are declared as objects of their respective class CharacterStats and
CharacterEquipment while the abilities need a List object of the BaseAbility class.
These are then initialized in the constructor when a BaseCharacter object is called and each get their own
get method to return them which are straight forward, except for the getCharacterName() which utilizes the
getAllCharacters() method residing in the GameEngine class.

In order to return the stats hit points, action points and energy level we instead utilize the CharacterStats class.
This class is responsible for returning the turn information during gameplay. This can be done by checking
which class instance is active and then return formatted String.
```
if (this instanceof SkeletonArcher || this instanceof SkeletonWarrior || this instanceof SkeletonMage) {
            return String.format(...);
        }
```

The class uses the boolean method isDead to check if the character is alive by simply returning true if the characters
hit points are less than or equal to 0.
In order to add abilities to the characters we may declare a method for this by setting the methods parameter equal
to the instance variable "abilities". This will be used when implementing each character to add the specified abilities.

The class will also make use of a Deque method to select which random ability that is to be used during gameplay.
It does this by iterating over a list of abilities and then randomizing the ability selected using the Randomizer class,
adding the ability to the Deque and returning it.

In order for characters to deal and receive damage, we need a method for calculating this since different amount of 
damage is to be made depending on the characters armor protection, defence rate and if the damage is done
using a magic ability or not. To do this we may declare an integer parameter to retreive the damage and a boolean
parameter to check if the damage is made using a magic ability or not.
The damage is then returned with both the mitigated amount and the actual damage made.

We also need to implement a solution that makes it possible for the characters to get healed. This is done 
using the method registerHealing() using an integer parameter. Here the method simply check if the healing amount
added to the current hit points is greater than the characters max hit points. If it is, it resets the characters
hit points to max and if its not, the character is healed with the healing amount.
```
 public int registerHealing(int heal) {

        int currentHP = getHitPoints();
        int maxHP = characterStats.getTotalHitPoints();
        if (currentHP + heal > maxHP) {
            characterStats.resetHitPoints();
        }
        else {
            characterStats.adjustHitPoints(heal);
        }
        return getHitPoints();
    }
```

There is also a need for a method that will be used to reset the action points and the energy level.
This is done in the same way as the registerHealing() method, but using a constant from the AppConfig class, instead
of declaring the method using a parameter.

Finally the toString() method. This makes it possible to show the heroes stats in the Hero Details screen.
It does so by simply returning a List containing information that we will get from the CharacterStats and 
CharacterEquipment classes later on.

#### CharacterEquipment

The CharacterEquipment class stores the weapons and armor pieces and calculates damage and armor protection.
We start by declaring these variables in the instance field. The weapons is stored in a List while the armor
is stored in a Map.
The get methods will then initialize these and return them.

The class need to return the total damage and armor protection, which is done in the same way for both.
Lets look at getTotalWeaponDamage().
To do this we declare a variable totalDamage to hold the total damage. We then iterate over the weapons list using a 
Weapon object and for each iteration we add the weapon damage to totalDamage using the getDamage() method
residing in the Weapon class. Once the iterations are complete, the totalDamage is returned.

Now we need to specify the amount of empty gear slots in order to not add to many weapons and armor pieces.
The amountOfEmptyArmorSlots() method simply done by returning the size of armorPieces subtracted from the max amount of slots.
The amountOfEmptyWeaponSlots() method is a bit more complicated since we need to adhere to the restriction
that a two-handed weapon only may be equipped once while a one-handed weapon can be equipped twice.
To do this we may first iterate over the weapons list using a Weapon object and for each iteration check if the weapon
is two-handed (using the isTwoHanded() method). If it is we remove all slots of the amount of empty slots and if it is
one-handed we remove one, which will allow it to add another one-handed weapon on the next iteration. Once completed
the method returns the used amount of slots.
```
int usedWeaponSlots = 0;

        for (Weapon weaponWield : weapons) {
            if (weaponWield.isTwoHanded()) {
                usedWeaponSlots += 2;
            }
            else {
                usedWeaponSlots += 1;
            }
        }

        return Math.max(0, 2 - usedWeaponSlots);
```

Now lets add some equipment! The addWeapon() and addArmorPiece() methods are similar since they both check if it is
possible to add another gear. We simply check if its allowed to add another gear, if it is the methods return true
and if its not, they return false.

The toString() method in the CharacterEquipment class works in the same way for both weapons and armor, so let us
break down the weapons part.
The this toString() will return what later on will be seen on the equipment screen. This includes the weapons
type, wield, damage, name and stat. 
To do this we iterate over the weapons list using a Weapon object and for each iteration we add the specified
information to a list before we return it.

#### CharacterStats
The CharacterStats class is responsible for providing various methods to retrieve and modify the stats in the game,
this includes the characters attributes, traits and combat stats. This is done using a combined Map for the three.
Therefor we simply create a single Map in the instance field.

The constructor will then be used to create a CharacterStats object with a parameter for the attributeValues and
initialize the stats Map and with the help of the StatsManager class we may retrieve the attribute names.
To do so we may use a loop to get the attribute names and values. The values will be multiplied with the 
ATTRIBUTE_BASE_VALUE found in the AppConfig class. We will then create a new Attribute object using the attribute name
and value using put method to add them into the stats Map as values and the attributeName as key.

The trait and combat stats will need to be added in a slightly different way since these do not have a common constant
as the attribute has its ATTRIBUTE_BASE_VALUE. For example the vitality trait has the constant 
TRAIT_VITALITY_BASE_VALUE while the energy trait use TRAIT_ENERGY_BASE_VALUE. 
Both the traits and combat stats are added in a similar way using the put method.

The traits are added using its trait name as key and the value as a new Trait object containing the trait name and value.
The combat stats uses the combat stat name as key but creates a new CombatStat object which takes three parameters instead
of two. That is since it first takes the stat name but then takes both an attribute and a trait.

The class then uses plenty of methods to return these types of stats.
We may start of with the getStat() and getStatValue() methods. The getStat() method simply returns the name of the stat,
by using its parameter to return the stats name from the stats Map.
The getStatValue also takes the statName as a parameter but uses it to create a new BaseStat object and then returns 
the method getModifiedValue() from the BaseStat class.

We will then need some methods to retrieve the total and current amount of action points, energy level and hit points.
This is done together with constants using the getStat() method to get the total amount, and the getStatValue to get the current amount.
The methods for returning the defence rate, attack rate, physical power, magical power and healing power are done
in the same way by returning their constants using the getStatValue() method.

In order to adjust the characters hit points, action points and energy level, we will create the adjustStatDynamicModifier()
method which takes two parameters. The stat name and the amount of value to adjust. We can do this by declaring a 
BaseStat object and assigning the stats value from the stats Map using the statName to get it.
We then use the adjustStaticModifier() method in the BaseStat class to change the value using the value received as 
an argument. This method is then used for the hit points, energy and action points to adjust them.
There is also a method called adjustStatStaticModifier which is used to set bonuses set on gear and for the vitality 
multiplying for the LichLord.

The methods for resetting the action points, energy level and hit points simply does this by creating a new BaseStat
object using the stats constant and then use the resetDynamicModifier() method residing in the BaseStat class.

The toString() method is responsible for returning a formatted list of all stat values which will be seen in the 
Hero Details screen under the STATISTICS field. This can be done by creating one main list and then a list 
for each row. We then format and add each column to the section list using the add method. An example of how this 
mey be done 
```
String formatStringBreak = "%s%-15s%s%3s%s%4s%-7s|";
String formatStringNoBreak = "%s%-15s%s%3s%s%4s";

formatListSection1.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.ATTRIBUTE_STRENGTH, AppConfig.ANSI_CYAN, getStatValue(AppConfig.ATTRIBUTE_STRENGTH), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.ATTRIBUTE_STRENGTH).getTotalModifier(), AppConfig.ANSI_WHITE));
formatListSection1.add(String.format(formatStringBreak, AppConfig.ANSI_GREEN, AppConfig.TRAIT_VITALITY, AppConfig.ANSI_CYAN, getStatValue(AppConfig.TRAIT_VITALITY), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.TRAIT_VITALITY).getTotalModifier(), AppConfig.ANSI_WHITE));
formatListSection1.add(String.format(formatStringNoBreak, AppConfig.ANSI_GREEN, AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ANSI_CYAN, getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS), AppConfig.ANSI_YELLOW, "+" + getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getTotalModifier()));

formatList.add(formatListSection1);
```
In this example we assign the formats used to a String to make it easier to change the formatting in the future.

Each section is then added to the main list and returned using the formatAsTable(method) alongside with the header.





### ActivityLogger
The ActivityLogger is used for the logging functionality during gameplay.
It does this by simply logging actions to the console and will also be used for some formatting of these messages.
The logger needs to be implemented as an eager singleton and since the INSTANCE variable is public there is no need
for a getInstance() method. We declare a new Logger object and initializes it in the constructor.

Since we need to write to the console we also implement a ConsoleHandler which is formatted and added to the logger. 
Here it is important to set its parent handlers to false in order to not print the messages twice to the console. 
There were some problems faced due to this that took a while to figure out.

As the project specification informs of, we will need to be able to execute a delay for the thread which is straight 
forward since the method is given in the assignment.

The method responsible for all the logging is called performLog and takes a String as its parameter.
This will print a message as long as there is no need for the delay to be executed.
```
private void performLog(String message) {
        if (AppConfig.USE_SLEEP_DELAY) {
            delayExecution();
        }
        logger.info(message);
    }
```

The rest of the methods will all take a String as its parameter and performLog using said parameter.



## Discussion