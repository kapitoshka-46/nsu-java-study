package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.stack;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.BaseCommand;

public class PushCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 1);
        System.out.println("pushing " + args[0]);
        double x = parseToken(ctx, args[0]);
        ctx.getStack().push(x);
    }

}
