package com.dt180g.laboration_2.components;

import com.dt180g.laboration_2.decorators.SpyMaster;
import com.dt180g.laboration_2.support.Constants;
import com.dt180g.laboration_2.support.InvalidAuthorizationException;

public abstract class Content implements MessageInterface {

    private static int encryptionLevel;
//    StringBuilder message = new StringBuilder();
    int offset;
    int alphaPos;

    protected static void increaseEncryptionLevel(int increaseLevel) {
    }

    protected static void setEncryptionLevel(int setLevel) {
    }

    protected static Content getEncryptionLevel(Content encryptionLevel, Object authenticator) {
        if (authenticator.getClass().equals(SpyMaster.class)) {
            return encryptionLevel;
        }
        else {
            throw new InvalidAuthorizationException("Only spy masters may access encryption depth.");
        }

    }


    protected StringBuilder cipher(String message, int rotValue) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (char ch : message.toCharArray()) {
            if (!Character.isAlphabetic(ch)) {
                encryptedMessage.append(ch);
            } else {
                if (Character.isUpperCase(ch)) {
                    offset = 'A';

                }
                if (Character.isLowerCase(ch)) {
                    offset = 'a';

                }
                alphaPos = ((ch - offset) + rotValue) % Constants.ALPHABET_LENGTH;
                char encryptedChar = (char) alphaPos;
                encryptedMessage.append(encryptedChar);

            }

        }
        return encryptedMessage;
    }
}

//    public static int getEncryptionLevel() {

//        if (encryptionLevel != SpyMaster) throw new InvalidAuthorizationException("Only spy masters may access encryption depth.")
//        return encryptionLevel;
//    }

    // cipher(String, int) : String


//        for (char ch = 0; ch < message.length(); ch++) {
//            if (!Character.isAlphabetic(ch)) {
//                encryptedMessage += ch;
//            }
//            else {
//                if (Character.isUpperCase(ch)) {
//                    offset = 65;
//
//                }
//                if (Character.isLowerCase(ch)) {
//                    offset = 97;
//
//                }
//
//            }
//            String alphaPos = ((ch - offset) + rotValue) % Constants.ALPHABET_LENGTH;



