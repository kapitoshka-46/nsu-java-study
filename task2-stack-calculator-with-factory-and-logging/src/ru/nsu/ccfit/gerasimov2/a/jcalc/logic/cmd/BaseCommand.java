package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd;

import ru.nsu.ccfit.gerasimov2.a.jcalc.exception.InvalidArgumentException;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;

public abstract class BaseCommand implements Command {
    public void validateArgs(String[] args, Integer number) throws InvalidArgumentException {

        if (args.length != number) {
            throw new InvalidArgumentException("Expected " + (number - 1) + " arguments");
        }
    }

    /**
     * 
     * @param ctx
     * @param token variable or double literal
     * @return value of tokent
     */
    public Double parseToken(Context ctx, String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return ctx.getMemory().getVar(token);
        }
    }
}
