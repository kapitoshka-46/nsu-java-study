package jcalc.logic.cmd.arithmetic;

import jcalc.logic.cmd.BaseCommand;
import jcalc.logic.Context;

import java.util.Stack;

public class DivideCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1); // only cmdName becuse operands are on the stack
        Stack<Double> stack = ctx.getStack();

        double first = stack.pop();
        double second = stack.pop();

        if (second == 0) {
            throw new ArithmeticException("Dividing by zero");
        }

        stack.push(first / second);
    }

}
