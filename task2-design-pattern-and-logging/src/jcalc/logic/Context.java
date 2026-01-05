package jcalc.logic;

import java.util.Stack;

/**
 * Access to the memory and stack
 */
public class Context {
    private Memory memory;
    private Stack<Double> stack;

    public Context() {
        memory = new Memory();
        stack = new Stack<>();
    }

    public Memory getMemory() {
        System.out.println("access to the memory");
        return memory;
    }

    public Stack<Double> getStack() {
        System.out.println("access to the stack");
        return stack;
    }

}
