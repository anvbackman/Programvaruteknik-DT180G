package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

/**
 * SpyMaster class which acts as the final component to our decorators.
 * @author Andreas Backman
 */
public class SpyMaster extends Operative {

    // Variable for storing the decryption key
    private final int decryptionKey;

    /**
     * Constructor for decrypting the encrypted message
     * @param content the decryptor
     */
    public SpyMaster(Content content) {
        super(content);
        // Creating the encryptionDepth variable pointing to getEncryptionLevel inside Content class
        // Then assigning the decryption calculation to the decryptionKey variable
        int encryptionDepth = Content.getEncryptionLevel(this);
        decryptionKey = Constants.ALPHABET_LENGTH - (encryptionDepth % Constants.ALPHABET_LENGTH);

    }

    /**
     * Method for overriding the getMessage method
     * @return the decrypted message and decryption key as String
     */
    @Override public String getMessage() { return cipher(super.getMessage(), decryptionKey); }
}
