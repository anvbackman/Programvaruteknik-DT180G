package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

public class Spy extends Operative {

    private int encryptValue;

    public Spy(Content content) {

//        int low = Constants.LOWER_BOUNDARY;
//        int high = Constants.UPPER_BOUNDARY;
        super(content);
        encryptValue = (Constants.UPPER_BOUNDARY - Constants.LOWER_BOUNDARY + 1) + Constants.LOWER_BOUNDARY;
        Content.increaseEncryptionLevel(encryptValue);
//        return encryptValue;
    }

    @Override public String getMessage() {
        return cipher(super.getMessage(), encryptValue);
    }

}
