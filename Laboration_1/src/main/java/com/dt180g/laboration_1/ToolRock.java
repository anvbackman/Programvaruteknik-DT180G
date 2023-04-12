package com.dt180g.laboration_1;

public class ToolRock extends Tool {

    // Setting the tool name
    public ToolRock() {
        super("Rock");
    }

    // Setting the tools weakness and returns it to getWeakness()
    public Tool getWeakness() {
        return new ToolPaper();
    }
}



