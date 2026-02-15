package ru.nsu.ccfit.gerasimov2.a.jcalc;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Main.class.getPackageName());

        log.info("Start program");
        try {
            CalculatorApp app = new CalculatorApp(args); // TODO: Let calculator parse args
            
            app.run();
        } catch (ParseException e) {
            System.out.println("Failed to parse command line options: " + e);
            System.out.println("type --help for help options");

            log.info(Level.INFO, "Bad parsing: ", e);
        } catch (FileNotFoundException e) { 
            log.error("File not found: ", e);
        }

        log.info("End program");
    }

}