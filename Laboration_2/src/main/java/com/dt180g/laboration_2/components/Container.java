package com.dt180g.laboration_2.components;

/**
 * Container class which acts as the storage of information
 * @author Andreas Backman
 */
public class Container extends Content {

    // Variable for holding the message
    private final String message;

    /**
     * Constructor for checking that the encryption level is a positive value and if not set the default level to 10
     * @param newMessage the new message
     * @param encryptLevel the encryption level
     */
    public Container(String newMessage, int encryptLevel) {

        if (encryptLevel < 0) { encryptLevel = 10; }

        setEncryptionLevel(encryptLevel);
        message = cipher(newMessage, encryptLevel);
    }

    /**
     * Method for overriding the getMessage method
     * @return the message as String
     */
    @Override public String getMessage() { return message; }
}
