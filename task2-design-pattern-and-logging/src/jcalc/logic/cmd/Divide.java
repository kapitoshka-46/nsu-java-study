package jcalc.logic.cmd;

import java.util.Stack;

import jcalc.logic.Context;

public class Divide extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1);
        Stack<Double> stack = ctx.getStack();

        double first = stack.pop();
        double second = stack.pop();

        if (second == 0) {
            throw new ArithmeticException("Dividing by zero");
        }

        stack.push(first / second);
    }

}
