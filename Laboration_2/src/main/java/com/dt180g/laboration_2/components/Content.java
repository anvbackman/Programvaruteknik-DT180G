package com.dt180g.laboration_2.components;

import com.dt180g.laboration_2.decorators.SpyMaster;
import com.dt180g.laboration_2.support.Constants;
import com.dt180g.laboration_2.support.InvalidAuthorizationException;


/**
 * Content class that serves as the base component from which the decorative implementations will derive
 * @author Andreas Backman
 */
public abstract class Content implements MessageInterface {

    // Variable to store the encryption level
    private static int encryptionLevel;


    /**
     * Method to increment the encryption level and return it
     *
     * @param increaseLevel the increased level
     * @return the increased encryptionLevel
     */
    protected static int increaseEncryptionLevel(int increaseLevel) {
        encryptionLevel = encryptionLevel + increaseLevel;
        return encryptionLevel;
    }

    /**
     * Method to set and return the encryptionLevel
     *
     * @param setLevel the newly set level
     * @return the set encryption level
     */
    protected static int setEncryptionLevel(int setLevel) {
        encryptionLevel = setLevel;
        return encryptionLevel;
    }

    /**
     * Method to check that the encryption level equals that of the SpyMaster and either returns the encryption level
     * or throws an exception
     *
     * @param content the content
     * @return the encryption level
     * @throws InvalidAuthorizationException if user is not SpyMaster
     */
    protected static int getEncryptionLevel(Content content) {
        if (content.getClass().equals(SpyMaster.class)) {
            return encryptionLevel;
        }
        else {
            throw new InvalidAuthorizationException("Only spy masters may access encryption depth.");
        }
    }

    /**
     * Method for ciphering the given message to encrypt it
     *
     * @param message the given message
     * @param rotValue the rotational value
     * @return the encrypted message
     */
    protected String cipher(String message, int rotValue) {
        StringBuilder encryptedMessage = new StringBuilder();
        // Variables for the encryption
        int offset = 0;
        int alphaPos;

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
}





