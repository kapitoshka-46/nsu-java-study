package jcalc.logic.cmd;

import jcalc.logic.Context;

public class HelpCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1);
        System.out.println("help message");
    }

}
