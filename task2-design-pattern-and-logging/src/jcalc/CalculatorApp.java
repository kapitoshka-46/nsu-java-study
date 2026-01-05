package jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import jcalc.logic.Context;
import jcalc.logic.cmd.*;

public class CalculatorApp {
    private final Context ctx;
    private final InputStreamReader in;
    private final PrintStream out;

    public CalculatorApp(InputStream in, PrintStream out) {
        ctx = new Context();
        this.in = new InputStreamReader(in);
        this.out = out;
    }

    private void tryExec(Command cmd, String[] args) {
        try {
            System.out.println("trying to execute command " + cmd.getClass().getName());
            cmd.execute(ctx, args);
        } catch (IllegalArgumentException e) {
            out.println(e.getLocalizedMessage());
        }
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(in)) {

            String line;
            out.print("> ");
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] args = line.split(" "); // TODO: use better regexp
                String cmdName = args[0];

                try {
                    Command cmd = CommandFactory.newCommand(cmdName);
                    tryExec(cmd, args);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                out.print("> ");
            }
        } catch (IOException e) {
            out.println("Error: " + e.getLocalizedMessage());
        }

    }
}
