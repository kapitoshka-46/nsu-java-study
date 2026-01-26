package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.Command;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.arithmetic.*;

public class ArtithmeticCmdTest {

    private void test3(Command cmd, double a, double b, double expected) {
        Context ctx = new Context(System.out);
        var stack = ctx.getStack();
        stack.push(a);
        stack.push(b);
        cmd.execute(ctx, new String[] { "plus" });
        assertEquals(expected, ctx.getStack().pop(), 0.001f);
    }

    @Test
    public void plus() {
        Command cmd = new PlusCommand();
        int a = 2;
        int b = 3;
        test3(cmd, a, b, a + b);
    }

    @Test
    public void plusZero() {
        Command cmd = new PlusCommand();
        int a = 2;
        int b = 0;
        test3(cmd, a, b, a);
    }

    @Test
    public void plusZeroZero() {
        Command cmd = new PlusCommand();
        int a = 0;
        int b = 0;
        test3(cmd, a, b, 0);
    }

    @Test
    public void minus() {
        Command cmd = new MinusCommand();
        double a = 2;
        double b = 3;
        test3(cmd, a, b, a - b);
    }

    @Test
    public void minusZero() {
        Command cmd = new MinusCommand();
        int a = 2;
        int b = 0;
        test3(cmd, a, b, a);
    }

    @Test
    public void minusZeroZero() {
        Command cmd = new MinusCommand();
        int a = 0;
        int b = 0;
        test3(cmd, a, b, 0);
    }

    @Test
    public void divide() {
        Command cmd = new DivideCommand();
        double a = 2;
        double b = 3;
        test3(cmd, a, b, a / b);
    }

    @Test
    public void dividePositiveByZero() {
        Command cmd = new DivideCommand();
        double a = 2;
        double b = 0;
        test3(cmd, a, b, Double.POSITIVE_INFINITY);
    }

    @Test
    public void divideNegativeByZero() {
        Command cmd = new DivideCommand();
        double a = -2;
        double b = 0;
        test3(cmd, a, b, Double.NEGATIVE_INFINITY);
    }

    @Test
    public void divideZeroByZero() {
        Command cmd = new DivideCommand();
        double a = 0;
        double b = 0;

        test3(cmd, a, b, Double.NaN);
    }

    @Test
    public void mult() {
        Command cmd = new MultCommand();
        double a = 2;
        double b = 3;
        test3(cmd, a, b, a * b);
    }

    @Test
    public void multZero() {
        Command cmd = new MultCommand();
        double a = 2;
        double b = 0;
        test3(cmd, a, b, a * b);
    }

}
