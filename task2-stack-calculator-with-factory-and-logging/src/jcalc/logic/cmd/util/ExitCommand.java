package jcalc.logic.cmd.util;

import jcalc.logic.Context;
import jcalc.logic.cmd.BaseCommand;

public class ExitCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        ctx.setRunning(false);
    }

}
