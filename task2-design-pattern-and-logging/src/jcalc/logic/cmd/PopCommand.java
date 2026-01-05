package jcalc.logic.cmd;

import jcalc.logic.Context;

public class PopCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 1); // only cmdName becuse operands are on the stack
        double x = ctx.getStack().pop();
        System.out.println("popped " + x);

    }

}
