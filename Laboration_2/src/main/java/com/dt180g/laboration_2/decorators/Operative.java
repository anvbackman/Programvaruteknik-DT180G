package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;

public abstract class Operative extends Content {

    private final Content content;

    protected Operative(Content content) {
        this.content = content;
    }

    @Override public String getMessage() {
        return content.getMessage();
    }
}
