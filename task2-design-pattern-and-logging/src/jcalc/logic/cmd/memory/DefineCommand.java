package jcalc.logic.cmd;

import jcalc.logic.Context;

public class DefineCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 3); // DEFINE a 4
        String varName = args[1];
        double value = parseToken(ctx, args[2]);
        ctx.getMemory().defineVar(varName, value);
    }

}
