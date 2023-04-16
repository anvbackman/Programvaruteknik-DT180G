package com.dt180g.laboration_1;

/**
 * The tool to be used in a game of Rock, Paper and Scissors.
 * @author Erik Ström
 */
public abstract class Tool {
    private final String toolName;

    // Getting the tools weaknesses from the extended classes
    public abstract Tool getWeakness();

    /**
     * Public construction which initialize members.
     * @param nameOfTool the name of tool
     */
    protected Tool(final String nameOfTool) {
        this.toolName = nameOfTool;
    }

    /**
     * Overridden method used by clients to output name of tool.
     * @return name of the tool
     */
    @Override public String toString() {
        return this.toolName.toLowerCase();
    }
}
