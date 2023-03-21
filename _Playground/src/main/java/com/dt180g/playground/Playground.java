package com.dt180g.playground;

/**
 * The main starting point for playground.
 * Note that this document is merely included to make sure correct folder
 * structure since Git does not version empty directories. This document
 * will thus be included in VCS, so you may want to experiment in other
 * source files if they should be ignored.
 * @author Erik Ström
 */
public final class Playground {
    private Playground() { // Utility classes should not have a public or default constructor
        throw new IllegalStateException("Utility class");
    }

    /**
     * Simple main execution.
     * @param args command line arguments
     */
    public static void main(final String... args) {
        System.out.println("This is your playground!");
    }
}
