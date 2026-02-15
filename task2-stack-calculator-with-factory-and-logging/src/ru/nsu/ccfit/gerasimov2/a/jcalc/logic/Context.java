package ru.nsu.ccfit.gerasimov2.a.jcalc.logic;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Stack;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.factory.Factory;

/**
 * Access to the memory and stack
 */
public class Context {
    private Memory memory;
    private Stack<Double> stack;
    public final PrintStream out;
    private boolean shouldClose = false;
    public final Factory factory;

    public Context(PrintStream out, Factory factory) {
        this.out = out;
        this.factory = factory;
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

    public Collection<String> getCommandsClassNames() {
        return factory.getCommandClassNames();
    }
}
