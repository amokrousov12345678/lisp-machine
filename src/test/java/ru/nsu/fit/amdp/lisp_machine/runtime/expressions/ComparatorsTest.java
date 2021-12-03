package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispEquals;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispLess;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

public class ComparatorsTest {
    static final Context comparatorsContext = ListOperationsTest.listOperationsContest;

    @BeforeAll
    static void fillContext() {
        ListOperationsTest.fillContext();
        comparatorsContext.define(new LispIdentifier("<"), new LispLess());
        comparatorsContext.define(new LispIdentifier("="), new LispEquals());
    }

    @Test
    public void equalsTest_true() throws ParseException {
        String expr = "(= 1 1)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void equalsTest_false() throws ParseException {
        String expr = "(= 1 3)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void equalsStringsTest_true() throws ParseException {
        String expr = "(= \"ab\" \"ab\")";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void equalsListsTest_true() throws ParseException {
        String expr = "(= (list 2 3) (list (- 4 2) (+ 1 (* 2 1) 0 0)))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void equalsListsTest_false() throws ParseException {
        String expr = "(= (list 2 3) (list 3 2))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void lessTest_true() throws ParseException {
        String expr = "(< 2 2.5)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void lessTest_false() throws ParseException {
        String expr = "(< 23 2.5)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(comparatorsContext);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void lessTest_wrong() throws ParseException {
        String expr = "(< 23 \"xd\")";
        var listExprs = TestParser.parseLispStatement(expr);

        var thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> listExprs.get(0).evaluate(comparatorsContext),
                comparatorsContext.getByName(new LispIdentifier("<")).toString() + " called with non numbers arguments");
    }
}
