package jcalc.logic.cmd.util;

import jcalc.logic.Context;
import jcalc.logic.cmd.BaseCommand;

public class HelpCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1);
        ctx.out.println("help message");
    }

}
