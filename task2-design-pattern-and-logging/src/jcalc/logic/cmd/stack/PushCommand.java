package jcalc.logic.cmd;

import jcalc.logic.Context;

public class PushCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 2);
        System.out.println("pushing " + args[1]);
        double x = parseToken(ctx, args[1]);
        ctx.getStack().push(x);
    }

}
