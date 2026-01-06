package jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.EmptyStackException;

import jcalc.logic.Context;
import jcalc.logic.cmd.Command;
import jcalc.logic.factory.Factory;

public class CalculatorApp {
    private final Context ctx;
    private final InputStreamReader in;
    private final PrintStream out;

    public CalculatorApp(InputStream in, PrintStream out) {
        this.in = new InputStreamReader(in);
        this.out = out;
        this.ctx = new Context(this.out);
    }

    private void tryExec(String cmdName, String[] args) {
        try {
            Factory factory = new Factory();
            Command cmd = factory.newCommand(cmdName);
            cmd.execute(ctx, args);
        } catch (EmptyStackException e) {
            out.println("ERROR: Stack is empty");
        } catch (Exception e) {
            out.println("ERROR: " + e.getLocalizedMessage());
        }

    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(in)) {

            String line;
            out.print("> ");
            while (ctx.isRunning() && (line = reader.readLine()) != null) {

                String[] args = line.split(" "); // TODO: use better regexp
                String cmdName = args[0];
                tryExec(cmdName, args);
                out.print("> ");
            }
        } catch (IOException e) {
            out.println("Error: " + e.getLocalizedMessage());
        }

    }
}
