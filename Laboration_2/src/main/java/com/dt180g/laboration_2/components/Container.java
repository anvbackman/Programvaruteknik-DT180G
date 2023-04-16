package com.dt180g.laboration_2.components;

public abstract class Container extends Content {

    private final String message;
    public Container(String newMessage, int encryptionLevel) {
//        this.message = message;

        if (encryptionLevel < 0) {
            encryptionLevel = 10;
        }

        setEncryptionLevel(encryptionLevel);
        message = String.valueOf(cipher(newMessage, encryptionLevel));


    }

    @Override
    public StringBuilder getMessage() {
        return message;
    }
}
