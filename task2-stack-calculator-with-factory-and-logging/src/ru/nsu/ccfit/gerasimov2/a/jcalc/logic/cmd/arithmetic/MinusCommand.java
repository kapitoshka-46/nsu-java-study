package ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.arithmetic;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.BaseCommand;

import java.util.Stack;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;

public class MinusCommand extends BaseCommand {
    @Override
    public void execute(Context ctx, String[] args) {
        validateArgs(args, 0);
        Stack<Double> stack = ctx.getStack();

        double first = stack.pop();
        double second = stack.pop();

        stack.push(first - second);
        System.out.println("pushed " + first + " - " + second);

    }
}
