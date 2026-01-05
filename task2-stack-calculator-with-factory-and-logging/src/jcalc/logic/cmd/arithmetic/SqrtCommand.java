package jcalc.logic.cmd.arithmetic;

import jcalc.logic.cmd.BaseCommand;
import jcalc.logic.Context;

import java.util.Stack;

public class SqrtCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1); // only cmdName becuse operands are on the stackls
        Stack<Double> stack = ctx.getStack();

        double x = stack.pop();

        stack.push(Math.sqrt(x));
        System.out.println("sqrt(" + x + ")");
    }

}
