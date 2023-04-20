package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

public class SpyMaster extends Operative {

    private final int decryptionKey;

    public SpyMaster(Content content) {
        super(content);

        int encryptionDepth = Content.getEncryptionLevel(this);
        decryptionKey = Constants.ALPHABET_LENGTH - (encryptionDepth % Constants.ALPHABET_LENGTH);

    }

    @Override public String getMessage() { return cipher(super.getMessage(), decryptionKey); }

}
