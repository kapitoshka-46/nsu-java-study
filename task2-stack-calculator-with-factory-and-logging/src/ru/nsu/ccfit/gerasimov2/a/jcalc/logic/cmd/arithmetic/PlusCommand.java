package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.arithmetic;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.BaseCommand;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;

import java.util.Stack;

public class PlusCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 0);
        Stack<Double> stack = ctx.getStack();

        double second = stack.pop();
        double first = stack.pop();

        stack.push(first + second);
        System.out.println("pushed " + first + " + " + second);

    }

}
