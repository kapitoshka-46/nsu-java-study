package jcalc.logic.cmd;

import jcalc.logic.cmd.arithmetic.*;
import jcalc.logic.cmd.memory.*;
import jcalc.logic.cmd.stack.*;

public class Factory { // TODO: use real factory design pattern - not a simple factory
    public static Command newCommand(String cmdName) throws IllegalArgumentException {
        if (!cmdName.isEmpty() && cmdName.charAt(0) == '#') {
            return new DummyCommand();
        }

        switch (cmdName) {
            case "":
                return new DummyCommand();
            case "+":
                return new PlusCommand();
            case "-":
                return new MinusCommand();
            case "*":
                return new MultCommand();
            case "/":
                return new DivideCommand();
            case "SQRT":
            case "sqrt":
                return new SqrtCommand();
            case "POP":
            case "pop":
                return new PopCommand();
            case "PUSH":
            case "push":
                return new PushCommand();
            case "define":
            case "DEFINE":
                return new DefineCommand();
            case "help":
            case "?":
                return new HelpCommand();
            case "print":
            case "PRINT":
                return new PrintCommand();
            default:
                throw new IllegalArgumentException("Command not found: " + cmdName);
        }
    }
}
