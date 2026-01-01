package jcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;

import jcalc.logic.Context;
import jcalc.logic.cmd.*;

public class CalculatorApp {
    private final Context ctx;
    private final Reader in;
    private final PrintWriter out;

    public CalculatorApp(Context ctx, Reader in, PrintWriter out) {
        this.ctx = ctx;
        this.in = in;
        this.out = out;
    }

    private void tryExec(Command cmd, String[] args) {
        try {
            cmd.execute(ctx, args);
        } catch (IllegalArgumentException e) {
            out.println(e.getLocalizedMessage());
        }
    }

    public void run() {
        out.println(">");
        try (BufferedReader reader = new BufferedReader(in)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] args = line.split(" "); // TODO: use good regexp
                String cmdName = args[0];

                BaseCommand cmd/* = Factyory.new(cmdName) */; // TODO
                tryExec(cmd, args);
            }
        } catch (IOException e) {
            out.println("Error: " + e.getLocalizedMessage());
        }

    }
}
