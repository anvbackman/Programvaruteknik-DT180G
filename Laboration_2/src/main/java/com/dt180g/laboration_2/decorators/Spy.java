package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

/**
 * Spy class that is a concrete syp operative deriving from the Operative class. Spy class is used to transport messages
 * @author Andreas Backman
 */
public class Spy extends Operative {

    // Variable for storing the encryption value
    private final int encryptValue;

    /**
     * Constructor for randomly increasing the encryption level for each spy added
     * @param content the randomizer
     */
    public Spy(Content content) {
        super(content);
        encryptValue = (int) Math.floor(Math.random() * (Constants.UPPER_BOUNDARY - Constants.LOWER_BOUNDARY + 1) + Constants.LOWER_BOUNDARY);
        Content.increaseEncryptionLevel(encryptValue);
    }

    /**
     * Method for overriding the getMessage method
     * @return the ciphered message and encryption value as String
     */
    @Override public String getMessage() { return cipher(super.getMessage(), encryptValue); }
}
