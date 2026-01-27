package ru.nsu.ccfit.gerasimov2.a.jcalc.logic;

import java.util.HashMap;
import java.util.Map;

import ru.nsu.ccfit.gerasimov2.a.jcalc.exception.InvalidArgumentException;

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
            throw new InvalidArgumentException("Cannot create var with empty name"); // TODO: make a WrongVariableNameException
        }
        char first = name.charAt(0);
        if (Character.isDigit(first)) {
            throw new InvalidArgumentException("The name of a var shouldn't start with digit");
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
     * @throws InvalidArgumentException if no var is defined with this name
     */
    public double getVar(String name) throws InvalidArgumentException {
        if (!varTable.containsKey(name)) {
            System.out.println("cannot find: " + name);
            throw new InvalidArgumentException("Var " + name + " is not defined.");
        }
        double x = varTable.get(name);
        return x;
    }
}
