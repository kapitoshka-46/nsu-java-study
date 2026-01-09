package ru.nsu.ccfit.gerasimov2.a.jcalc.logic;

import java.util.HashMap;
import java.util.Map;
import java.lang.IllegalArgumentException;

public class Memory {
    private Map<String, Double> varTable;

    /**
     * Stores the variable to the memory
     * 
     * @param name
     * @param value
     */
    public void defineVar(String name, double value) {
        checkVarName(name);
        System.out.println(name + " = " + value);
        varTable.put(name, value);
    }

    private void checkVarName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Cannot create var with empty name");
        }
        char first = name.charAt(0);
        if (Character.isDigit(first)) {
            throw new IllegalArgumentException("The name of a var shouldn't start with digit");
        }

    }

    public Memory() {
        varTable = new HashMap<String, Double>();
    }

    /**
     * Trying to find a value in the memory
     * 
     * @param name
     * @return Value of the var with this name
     * @throws IllegalArgumentException if no var is defined with this name
     */
    public double getVar(String name) throws IllegalArgumentException {
        if (!varTable.containsKey(name)) {
            System.out.println("cannot find: " + name);
            throw new IllegalArgumentException("Var " + name + " is not defined.");
        }
        double x = varTable.get(name);
        return x;
    }
}
