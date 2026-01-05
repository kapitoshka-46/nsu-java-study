package jcalc;

public class Main {

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");
        CalculatorApp app = new CalculatorApp(System.in, System.out); // TODO: Let calculator parse args
        app.run();

    }

}