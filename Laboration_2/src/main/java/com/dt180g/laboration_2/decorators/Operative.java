package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;

/**
 * Operative class
 * @author Andreas Backman
 */
public abstract class Operative extends Content {

    // Variable for storing content
    private final Content content;

    /**
     * Constructor for setting content to content
     * @param content
     */
    protected Operative(Content content) { this.content = content; }

    /**
     * Method for overriding the getMessage method
     * @return the message as String
     */
    @Override public String getMessage() { return content.getMessage(); }
}
