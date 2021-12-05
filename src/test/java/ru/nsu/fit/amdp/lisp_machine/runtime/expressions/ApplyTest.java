package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

import java.io.IOException;

public class ApplyTest {
    private final Machine machine = new Machine();

    @Test
    public void testScalarArg() throws ParseException {
        String expr = "(apply - 4)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), -4);
    }

    @Test
    public void testListArg() throws ParseException {
        String expr = "(apply + (list 1 2 3 4))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), 10);
    }

    @Test
    public void testNamedArg() throws ParseException {
        String declarations = "(def x (list 1 2 3 4))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        String expr ="(apply + x)";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), 10);
    }

    @Test
    public void testFunctionArg() throws ParseException, IOException {
        String declarations = "(defn plus (n) (apply + n))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.loadStandardLibrary();
        machine.eval(listExprs);

        String expr ="(plus (list 1 2 3 4))";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), 10);
    }
}
