package jcalc.logic.cmd.stack;

import jcalc.logic.Context;
import jcalc.logic.cmd.BaseCommand;

public class PopCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 1); // only cmdName becuse operands are on the stack
        double x = ctx.getStack().pop();
        System.out.println(x);

    }

}
