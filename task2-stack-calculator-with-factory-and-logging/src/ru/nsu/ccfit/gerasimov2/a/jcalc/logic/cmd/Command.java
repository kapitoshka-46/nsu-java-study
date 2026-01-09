package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;

public interface Command {
    /**
     * 
     * @param ctx  Access to the memory and stack
     * @param args Array of arguments in C style: args[0] is the name of command,
     *             args[1] is the first agrument
     */
    void execute(Context ctx, String[] args);
}