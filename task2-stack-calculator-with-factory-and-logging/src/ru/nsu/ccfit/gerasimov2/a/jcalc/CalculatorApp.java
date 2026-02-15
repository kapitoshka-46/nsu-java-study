package ru.nsu.ccfit.gerasimov2.a.jcalc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

import ru.nsu.ccfit.gerasimov2.a.jcalc.exception.CalculatorException;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.Command;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.factory.Factory;

import org.apache.commons.cli.*;

public class CalculatorApp {
    private BufferedReader in = null;
    private final PrintStream out = System.out;
    private final Factory factory;
    private final Context ctx;

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

    private void printHelpMessage(Options options) {
        @SuppressWarnings("deprecation")
        HelpFormatter formatter = new HelpFormatter(); 
        String cmdLineSyntax = "jcalc [options] <input file (not neccessary)>"; // The usage statement
        formatter.printHelp(100, cmdLineSyntax, "List of options:", options, "");
    }

    private void parsePositionalArguments(CommandLine cmdLine) throws FileNotFoundException {
        String[] posArgs = cmdLine.getArgs();
        if (posArgs.length == 0) {
            isFileMode = false;
            in = new BufferedReader(new InputStreamReader(System.in));
        } else {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(posArgs[0])));
        }
    }
    private void setUp(String[] args) throws FileNotFoundException, ParseException { 
        DefaultParser parser = new DefaultParser();
        
        Options options = new Options();
        options.addOption("d", "debug", false, "Enable logging to file");
        options.addOption("h", "help", false, "Print this message");
        
        CommandLine cmdLine = parser.parse(options, args); // TODO: print help if parse excetption and close program
        if (cmdLine.hasOption("help")) {
                printHelpMessage(options);
                ctx.setShouldClose(isFileMode);
        }
        parsePositionalArguments(cmdLine);

    }
    public CalculatorApp(String[] args) throws FileNotFoundException, ParseException {
        this.factory = new Factory();
        this.ctx = new Context(out, factory);
        setUp(args);
    }

    private void printError(String msg) {
        if (isFileMode) {
            out.printf("Error at line %d: %s\n", lineCount, msg);
        } else {
            out.println("Error: " + msg);
        }
    }

    private void tryExec(String cmdName, String[] args, Factory factory) {
        if (cmdName.isEmpty()) {
            return;
        }

        try {
            Command cmd = factory.newCommand(cmdName);
            cmd.execute(ctx, args);
        } catch (CalculatorException e) {
            printError(e.getLocalizedMessage());
            if (isFileMode) {
                ctx.setShouldClose(true);
            }
        }
    }

    private void printPrompt() {
        if (!isFileMode) {
            out.print(prompt);
        }
    }

    public void run() {
        if (ctx.shouldClose()) return;

        try (BufferedReader reader = new BufferedReader(in)) {
            printPrompt();

            String line;
            while (!ctx.shouldClose() && (line = reader.readLine()) != null) {
                lineCount++;
                String[] args = line.split(" "); // TODO: use better regexp
                String cmdName = args[0];
                tryExec(cmdName, Arrays.copyOfRange(args, 1, args.length), factory);
                printPrompt();
            }
        } catch (IOException e) {
            out.println("IOException: " + e.getLocalizedMessage());
        }

    }
}
