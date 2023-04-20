package com.dt180g.laboration_2.components;

public class Container extends Content {

    private final String message;
    public Container(String newMessage, int encryptionLevel) {


        if (encryptionLevel < 0) {
            encryptionLevel = 10;
        }

        setEncryptionLevel(encryptionLevel);
        message = String.valueOf(cipher(newMessage, encryptionLevel));


    }

    @Override public String getMessage() {
        return message;
    }
}
