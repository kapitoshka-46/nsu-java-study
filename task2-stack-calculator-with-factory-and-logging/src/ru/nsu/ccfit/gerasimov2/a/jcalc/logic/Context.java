package ru.nsu.ccfit.gerasimov2.a.jcalc.logic;

import java.io.PrintStream;
import java.util.Stack;

/**
 * Access to the memory and stack
 */
public class Context {
    private Memory memory;
    private Stack<Double> stack;
    public final PrintStream out;
    private boolean shouldClose = false;

    public Context(PrintStream out) {
        this.out = out;
        memory = new Memory();
        stack = new Stack<>();
    }

    public Memory getMemory() {
        return memory;
    }

    public Stack<Double> getStack() {
        return stack;
    }

    public boolean shouldClose() {
        return shouldClose;
    }

    public void setShouldClose(boolean shouldClose) {
        this.shouldClose = shouldClose;
    }
}
