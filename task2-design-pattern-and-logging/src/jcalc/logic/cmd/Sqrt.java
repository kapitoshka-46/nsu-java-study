package jcalc.logic.cmd;

import java.util.Stack;

import jcalc.logic.Context;

public class Sqrt extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1);
        Stack<Double> stack = ctx.getStack();

        double x = stack.pop();

        stack.push(Math.sqrt(x));
    }

}
