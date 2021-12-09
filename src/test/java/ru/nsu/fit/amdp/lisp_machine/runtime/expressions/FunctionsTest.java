package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
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
        Assertions.assertTrue(((LispObject) result).self() instanceof Long);
        Assertions.assertEquals(((LispObject) result).self(), 120L);
    }

    @Test
    public void factorialReorderedCallsTest() throws ParseException {
        String declarations = "(def fact (fn (n) (if (< n 2) 1 (* (fact (- n 1)) n))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        String expr = "(fact 6)";
        var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Long);
        Assertions.assertEquals(((LispObject) result).self(), 720L);
    }

    @Test
    public void fibonacciTest() throws ParseException {
        String declarations = "(def fib (fn (n) (if (< n 2) 1 (+ (fib (- n 1)) (fib (- n 2))))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        {
            String expr = "(fib 6)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 13L);
        }

        {
            String expr = "(fib 11)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 144L);
        }
    }

    @Test
    public void ackermannTest() throws ParseException {
        String declarations = "(def ackermann (fn (m n) (if (= m 0) (+ n 1)" +
                "(if (= n 0) (ackermann (- m 1) 1) (ackermann (- m 1) (ackermann m (- n 1)))))))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        {
            String expr = "(ackermann 0 0)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 1L);
        }

        {
            String expr = "(ackermann 3 5)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 253L);
        }

        {
            String expr = "(ackermann 2 (+ 5 5 2 3))";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 33L);
        }
    }

    @Test
    public void closureTest() throws ParseException {
        String declarations = "(def adder (fn (x) (fn (n) (+ n x))))" +
                "(def plus5 (adder 5))" +
                "(def minus3 (adder (- 3)))";
        var listExprs = TestParser.parseLispStatement(declarations);
        machine.eval(listExprs);

        {
            String expr = "(plus5 10)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 15L);
        }

        {
            String expr = "(minus3 50)";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 47L);
        }

        {
            String expr = "((adder 10) ((adder (- 20)) 15))";
            var result = machine.eval(TestParser.parseLispStatement(expr).get(0));

            Assertions.assertTrue(result instanceof LispObject);
            Assertions.assertTrue(((LispObject) result).self() instanceof Long);
            Assertions.assertEquals(((LispObject) result).self(), 5L);
        }
    }
}
