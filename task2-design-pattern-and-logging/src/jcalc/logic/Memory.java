package jcalc.logic;

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
        varTable.put(name, value);
    }

    public Memory() {
        varTable = new HashMap<String, Double>();
    }

    /**
     * 
     * @param name
     * @return Value of the var with this name
     * @throws IllegalArgumentException if no var is defined with this name
     */
    public double getVar(String name) throws IllegalArgumentException {
        if (!varTable.containsKey(name)) {
            throw new IllegalArgumentException("Var '" + name + "' is not defined.");
        }
        return varTable.get(name);
    }
}
