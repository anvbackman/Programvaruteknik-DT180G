package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

public class Spy extends Operative {

    private int encryptValue;

    public int Spy(Content content) {

//        int low = Constants.LOWER_BOUNDARY;
//        int high = Constants.UPPER_BOUNDARY;

        encryptValue = (Constants.UPPER_BOUNDARY - Constants.LOWER_BOUNDARY + 1) + Constants.LOWER_BOUNDARY;
        return encryptValue;
    }

    @Override
    public StringBuilder getMessage() {
        return cipher(super.getMessage().toString(), encryptValue);
    }

}
