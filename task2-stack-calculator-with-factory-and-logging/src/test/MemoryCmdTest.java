package test;


import org.junit.Test;

import ru.nsu.ccfit.gerasimov2.a.jcalc.exception.InvalidArgumentException;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.Context;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.Command;
import ru.nsu.ccfit.gerasimov2.a.jcalc.logic.cmd.memory.DefineCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class MemoryCmdTest {

    private void testVar(String name, Double value) {
    
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) throws IOException {}
        }       
        ));

        Context ctx = new Context(System.out);
        Command cmd = new DefineCommand();
        String[] args = {name, value.toString()};
        cmd.execute(ctx, args);

        assertEquals(ctx.getMemory().getVar(name), value, 0.001);
    
    }

    @Test
    public void BasicTests() {
        testVar("abacaba", 10.345);
        testVar("name", 0.000123);
    }

    @Test 
    public void NameBeginsWithDigits() {
        assertThrows(InvalidArgumentException.class, 
            () -> { testVar("123name", 1.25 );} 
        );
    }

}
