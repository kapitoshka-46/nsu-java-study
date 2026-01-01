package jcalc.logic;

import java.util.Stack;

/**
 * Access to the memory and stack
 */
public class Context {
    private Memory memory;
    private Stack<Double> stack;

    public Memory getMemory() {
        return memory;
    }

    public Stack<Double> getStack() {
        return stack;
    }

}
