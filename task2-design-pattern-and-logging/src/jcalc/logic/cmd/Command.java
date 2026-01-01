package jcalc.logic.cmd;

import jcalc.logic.Context;
import java.lang.IllegalArgumentException;

public interface Command {
    /**
     * 
     * @param ctx  Memory and stack
     * @param args Array of arguments in C style: args[0] is the name of command,
     *             args[1] is the first agrument
     * @throws IllegalArgumentException
     */
    void execute(Context ctx, String[] args) throws IllegalArgumentException;
}