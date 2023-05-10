# Laboration 2

---
## Environments & Tools
Lenovo Ideapad 5, Windows 10, IntelliJ IDEA, Java, Git 2.37.3, Google Chrome,
Windows Powershell, Bitbucket

---
## Purpose
The aim of this lab is to demonstrate the application of the Decorator Design Pattern in a secure message
transmission scenario, where messages need to be encrypted and decrypted while being transferred
between spy handlers through field agents. The provided design needs to be implemented, ensuring that the
encryption and decryption processes work as intended, and the solution adheres to the Decorator Design
Pattern principles.

#### Concrete goals:
* Implement the Content class as the abstract base component, in compliance to its specification, and
  implement the Operative class as the abstract base decorator, from which concrete decorators will
  derive
* Implement the Container class as the concrete component, responsible for the base storage of the
   encrypted message content and initial encryption.
* Implement the Spy class as a concrete decorator, responsible for increasing the encryption level
  each time a new Spy is added to the decoration chain.
* Implement the SpyMaster class as a concrete decorator, responsible for decrypting the encrypted
  message and applying the decryption key based on the actual encryption depth.
*  Ensuring that the solution complies with design specifications, the provided client code, and passes
   the provided unit tests.

---   
## Procedures

### Content class
According to the class diagram, the content class should be abstract and also implement the MessageInterface
interface. So the first step is to specify this in the class declaration. 
A static variable is then created to store the encryption level.

In order to be able to increase the encryption level, we then create the method increaseEncryptionLevel with 
the int parameter increaseLevel. To increase the encryption level, we simply take the current encryption level
and add the applied increaseLevel and then return the new encryption level.
```
protected static int increaseEncryptionLevel(int increaseLevel) {
    encryptionLevel = encryptionLevel + increaseLevel;
    return encryptionLevel;
}
```

To then set the incryption level, the method setEncryptionLevel() is created with the parameter int setLevel.
Simular to the increaseEncryptionLevel method, we simply set the encryption level equal to the setLevel parameter
and finally returns the value.
```
protected static int setEncryptionLevel(int setLevel) {
    encryptionLevel = setLevel;
    return encryptionLevel;
}
```

We then need to check if the user is a spy master or a regular spy. If the user is a spy master the encryption level
shall be returned and if the user is a regular spy, a InvalidAuthorizationException needs to be thrown.

In order to throw the InvalidAuthorizationException we first need to create the exception class.
Firstly the class need to extend the RuntimeException class. 
A constructor is then created to return the message to the exception.
```
public class InvalidAuthorizationException extends RuntimeException {
    public InvalidAuthorizationException(String message) { super(message); }
}
```
The method getEncryptionLevel() is created using the parameter Content content.
To check if the user is a spy master or a regular spy, we can create an if / else statement.
In the if statement we simply check if the object content equals the SpyMaster class object.
And if it doesn't, we throw the InvalidAuthorizationException using the requested message.
```
protected static int getEncryptionLevel(Content content) {
        if (content.getClass().equals(SpyMaster.class)) {
            return encryptionLevel;
        }
        else {
            throw new InvalidAuthorizationException("Only spy masters may access encryption depth.");
        }
    }
```

Lastly we'll need to cipher the messages in order to shift the messages in either direction.
The method cipher is created with two parameters, String message and int rotValue. 
We start off by creating a StringBuilder (encryptedMessage) that we later will store our characters and the 
int variables offset and alfaPos.
```
protected String cipher(String message, int rotValue) {
        StringBuilder encryptedMessage = new StringBuilder();
        // Variables for the encryption
        int offset = 0;
        int alphaPos;
```
By iterating over the message String after converting it to an character array, we first need to exclude
any digits. 
```
for (char ch : message.toCharArray()) {
            // Checking if the char is an alphabetical character. Else it stores the offset depending on
            // lower or upper case char
            if (!Character.isAlphabetic(ch)) {
                encryptedMessage.append(ch);
```
Else the offset of the characters are set depending on if they're upper or lower case.
The alpha position is then calculated using the given calculations and is then appended to the string builder
and returned toString.
```
for (char ch : message.toCharArray()) {
            // Checking if the char is an alphabetical character. Else it stores the offset depending on
            // lower or upper case char
            if (!Character.isAlphabetic(ch)) {
                encryptedMessage.append(ch);
            } else {
                if (Character.isUpperCase(ch)) {
                    offset = 65;

                }
                if (Character.isLowerCase(ch)) {
                    offset = 97;

                }
                // Calculating the offset and then converting it to a char to be appended to the encryptedMessage
                alphaPos = ((ch - offset) + rotValue) % Constants.ALPHABET_LENGTH;
                char encryptedChar = ((char)(alphaPos + offset));
                encryptedMessage.append(encryptedChar);
            }
        }
        return encryptedMessage.toString();
    }
```

### Operative class

The abstract operative class will be used as a decorator and extends the Content class.
We start by creating the private final variable Content content.
A constructor is then created which will set this content to content, this is straight forward but one must
make sure that the constructor is protected.
```
protected Operative(Content content) { this.content = content; }
```
In order to override the getMessage String, we simply return it using the content object.
```
protected Operative(Content content) { this.content = content; }
```


### Container class
Same as the Operative class, this class will extend the Content class.
This class will store the message content, hence we start with creating a variable to store it.

The constructor will be used to check that the encryption level is high enough. Using the parameters newMessage
and encryptLevel we can first state that if the encryption level is negative, we set it to 10.
```
if (encryptLevel < 0) { encryptLevel = 10; }
```
We then set the encryption level to the new encryption level and assign the ciphered newMessage and encryptLevel
to the message String, which will then be returned using the getMessage() method.
```
@Override public String getMessage() { return message; }
```

### Spy class
The regular spy class will extend the Operative class and will be used to transport messages and to
increase the encryption level.
By first creating the variable encryptValue, we may use this to later calculate the randomized new level.
This is done in the constructor which is created with the parameter Content content. 
The randomized encryption value is calculated and then sent to Content.increaseEncryptionLevel.
```
public Spy(Content content) {
        super(content);
        encryptValue = (int) Math.floor(Math.random() * (Constants.UPPER_BOUNDARY - Constants.LOWER_BOUNDARY + 1) + Constants.LOWER_BOUNDARY);
        Content.increaseEncryptionLevel(encryptValue);
    }
```
The encryption value is then returned via the getMessage() method where it is ciphered.
```
@Override public String getMessage() { return cipher(super.getMessage(), encryptValue); }
```

### SpyMaster class
The SpyMaster class will also extend the Operative class but the SpyMaster class will also be able to read
the encrypted messages by decrypting them.
In order to do so we first create a variable called decryptionKey.
We then create the constructor using the parameter Content content and set the encryptionDepth.
The decryptionKey variable is then used to hold the calculation that will reverse the encryption calculation
using the given calculation.
```
int encryptionDepth = Content.getEncryptionLevel(this);
        decryptionKey = Constants.ALPHABET_LENGTH - (encryptionDepth % Constants.ALPHABET_LENGTH);
```
The decryption is then returned using the cipher in the getMessage() method.

## Discussion

### Validation
In order to validate that the implementation works and that the purposes has been fulfilled, the program has been
executed several times and Maven has been used to run unit tests without failure. 

The Content class has been implemented as the abstract base component and adheres to the specification shown
in the class diagram and the abstract base decorator Operative has been implemented so from which the decorators
can derive.
The Container class has been created to store the encrypted message and base encryption.

The Spy class has been implemented so that increase of encryption value is possible. This by checking 
that the encryption level is positive. If not, the spy class will set it to 10. The spy class will then
return the increased encryption value.

The SpyMaster class is created according to the specifications and is the only one who can decipher the
encrypted messages, by reversing the calculation that encrypted it. It then sets the encryption depth and
returns the decryption key.

The solution is in compliance with the design diagram and the provided code and the unit tests has been
run and passed. At first there was an error encountered when running the unit tests, but that was solved
rather easy.

By reaching the goals set, it can be established that the purposes of this assignment has been fulfilled.

### Personal Reflections
Through this lab and its modules, I feel that I have gained a lot more knowledge about interfaces, decorators and unit testing.
I've had my ups and downs with the lab, but it has been enjoyable getting to solve them. I would say that
the course literature and its exercises has helped quite a bit, since it has been a couple of simular 
exercises to what was expected in this lab.

