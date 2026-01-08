package jcalc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import jcalc.logic.Context;
import jcalc.logic.cmd.Command;
import jcalc.logic.factory.Factory;

import org.apache.commons.cli.*;

public class CalculatorApp {
    private BufferedReader in = null;
    private final PrintStream out = System.out;
    private final Context ctx = new Context(out);

    /**
     * {@code true} if input file was provided. if {@code flase} then calculator
     * works as
     * interpreter (e.g. no printing number of line where error caused or print
     * prompt "> ")
     */
    private boolean isFileMode = true;
    private final String prompt = "> ";

    /** Current line in file. Do not use it if in calculator works in file mode */
    private int lineCount = 0;

    public CalculatorApp(String[] args) {
        // TODO: reorgonize and simplify this shit ...
        DefaultParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption(null, "debug", false, "Debug mode");
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(options, args);
        } catch (ParseException e) {
            out.println("ERROR: Failed to parse command line options: " + e.getLocalizedMessage());
            System.exit(1);
        }

        String[] posArgs = cmdLine.getArgs();
        if (posArgs.length == 0) {
            isFileMode = false;
            in = new BufferedReader(new InputStreamReader(System.in));
        } else {
            try {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(posArgs[0])));
            } catch (FileNotFoundException e) {
                out.println("ERROR: " + e.getLocalizedMessage());
            } finally {
                if (in == null) {
                    System.exit(2);
                }
            }
        }
    }

    private void tryExec(String cmdName, String[] args, Factory factory) {
        if (cmdName.isEmpty()) {
            return;
        }

        try {
            Command cmd = factory.newCommand(cmdName);
            cmd.execute(ctx, args);
        } catch (Exception e) {
            if (isFileMode) {
                out.printf("Error at line %d: %s\n", lineCount, e.getLocalizedMessage());
            } else {
                out.println("Error: " + e.getLocalizedMessage());
            }
        }

    }

    private void printPrompt() {
        if (!isFileMode) {
            out.print(prompt);
        }
    }

    public void run() {
        Factory factory = new Factory();

        try (BufferedReader reader = new BufferedReader(in)) {

            String line;
            printPrompt();
            while (ctx.isRunning() && (line = reader.readLine()) != null) {
                lineCount++;
                String[] args = line.split(" "); // TODO: use better regexp
                String cmdName = args[0];
                tryExec(cmdName, args, factory);
                printPrompt();
            }
        } catch (IOException e) {
            out.println("IOException: " + e.getLocalizedMessage());
            System.exit(3);
        }

    }
}
