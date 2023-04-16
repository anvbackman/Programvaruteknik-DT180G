package com.dt180g.laboration_1;

public class ToolScissors extends Tool {

    // Setting the tool name
    public ToolScissors() {
        super("Scissors");
    }

    // Setting the tools weakness and returns it to getWeakness()
    @Override
    public Tool getWeakness() {
        return new ToolRock();
    }
}
