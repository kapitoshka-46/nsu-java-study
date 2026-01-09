package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import java.lang.IllegalArgumentException;
import java.util.EmptyStackException;

public interface Command {
    /**
     * 
     * @param ctx  Access to the memory and stack
     * @param args Array of arguments in C style: args[0] is the name of command,
     *             args[1] is the first agrument
     * @throws IllegalArgumentException if args are not valid for this command
     */
    void execute(Context ctx, String[] args) throws IllegalArgumentException, EmptyStackException;
}