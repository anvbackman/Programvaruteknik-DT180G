package com.dt180g.laboration_2.support;

/**
 * InvalidAuthorizationException class to give an exeption if user is not a SpyMaster
 * @author Andreas Backman
 */
public class InvalidAuthorizationException extends RuntimeException {
    /**
     * Constructor for the InvalidAutorizationExeption
     *
     * @param message the exeption message
     */
    public InvalidAuthorizationException(String message) { super(message); }
}
