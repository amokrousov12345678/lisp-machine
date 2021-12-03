package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

public class FunctionsTest {
    private final Machine machine = new Machine();

    @Test
    public void userDefinedLogicFunctionsTest() throws ParseException {
        String declarations = "(def != (fn (a b) (! (= a b))))\n" +
                              "(def > (fn (a b) (and (! (< a b)) (!= a b))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        String expr = "(and (> 5 2) (> 3 1) (!= 2 1))";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void factorialTest() throws ParseException {
        String declarations = "(def fact (fn (n) (if (< n 2) 1 (* n (fact (- n 1))))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        String expr = "(fact 5)";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), 120);
    }

    @Test
    public void factorialReorderedCallsTest() throws ParseException {
        String declarations = "(def fact (fn (n) (if (< n 2) 1 (* (fact (- n 1)) n))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        String expr = "(fact 6)";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(((LispObject) result).self(), 720);
    }
}
