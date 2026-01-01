package jcalc.logic.cmd;

import java.lang.IllegalArgumentException;

import jcalc.logic.Context;

public abstract class BaseCommand implements Command {
    public void validateArgs(String[] args, Integer number) {

        if (args.length != number) {
            throw new IllegalArgumentException("Expected " + (number - 1) + " arguments");
        }
    }

    public Double parseToken(Context ctx, String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return ctx.getMemory().getVar(token);
        }
    }
}
