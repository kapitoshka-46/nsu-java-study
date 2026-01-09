package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.util;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.BaseCommand;

public class HelpCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 1);
        ctx.out.println("help message");
    }

}
