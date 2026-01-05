package jcalc.logic.cmd;

public class CommandFactory { // TODO: use real factory design pattern - not a simple factory
    public static Command newCommand(String cmdName) throws IllegalArgumentException {
        System.out.println("factory: " + cmdName);
        switch (cmdName) {
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
            default:
                throw new IllegalArgumentException("Unknown command: " + cmdName);
        }
    }
}
