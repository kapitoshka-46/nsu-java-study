package jcalc.logic.cmd.arithmetic;

import jcalc.logic.cmd.BaseCommand;
import jcalc.logic.Context;

import java.util.Stack;

public class PlusCommand extends BaseCommand {

    @Override
    public void execute(Context ctx, String[] args) throws IllegalArgumentException {
        validateArgs(args, 1); // only cmdName becuse operands are on the stack
        Stack<Double> stack = ctx.getStack();

        double first = stack.pop();
        double second = stack.pop();

        stack.push(first + second);
        System.out.println("pushed " + first + " + " + second);

    }

}
