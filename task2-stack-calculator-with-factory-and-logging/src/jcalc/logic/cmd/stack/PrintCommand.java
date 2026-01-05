package jcalc.logic.cmd.stack;

import jcalc.logic.Context;
import jcalc.logic.cmd.BaseCommand;

public class PrintCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        System.out.println(ctx.getStack().peek());
    }

}
