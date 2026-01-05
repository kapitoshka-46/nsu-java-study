package jcalc.logic.cmd.stack;

import jcalc.logic.Context;
import jcalc.logic.cmd.BaseCommand;

public class PushCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 2);
        System.out.println("pushing " + args[1]);
        double x = parseToken(ctx, args[1]);
        ctx.getStack().push(x);
    }

}
