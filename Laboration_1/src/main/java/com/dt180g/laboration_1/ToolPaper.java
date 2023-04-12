package com.dt180g.laboration_1;

public class ToolPaper extends Tool {

    // Setting the tool name
    public ToolPaper() {
        super("Paper");
    }

    // Setting the tools weakness and returns it to getWeakness()
    public Tool getWeakness() {
        return new ToolScissors();
    }
}
