package ru.nsu.ccfit.gerasimov2.a.jcalc;

import java.io.FileNotFoundException;

import org.apache.commons.cli.ParseException;

public class Main {

    public static void main(String[] args) {
        try {
            CalculatorApp app = new CalculatorApp(args); // TODO: Let calculator parse args
            app.run();
        } catch (ParseException e) {
            System.out.println("ERROR: Failed to parse command line options: " + e.getLocalizedMessage());
            System.out.println("type --help for help options");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
        }
    }

}