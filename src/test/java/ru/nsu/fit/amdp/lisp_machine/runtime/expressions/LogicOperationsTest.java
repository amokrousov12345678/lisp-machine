package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.Machine;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.LispAnd;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.LispOr;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;
import java.util.List;

public class LogicOperationsTest {
    private final Machine machine = new Machine();

    @Test
    public void testNot_true() throws ParseException {
        String expr = "(! (< 4 4))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void testNot_false() throws ParseException {
        String expr = "(! (< 3 4))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void testAnd_true() throws ParseException {
        String expr = "(and true true true true true)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void testAnd_false() throws ParseException {
        String expr = "(and true false true)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void testOr_true() throws ParseException {
        String expr = "(or false true true false true)";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void testOr_false() throws ParseException {
        String expr = "(and false false (! true))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = machine.eval(listExprs.get(0));

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    private class Thrower implements Expression {
        @Override
        public Expression evaluate(Context context) {
            throw new RuntimeException("Should not be invoked");
        }
    }

    @Test
    public void testAndLaziness() {
        List<Expression> args = List.of(
                new LispObject(true),
                new LispObject(true),
                new LispObject(false),
                new Thrower(), new Thrower(), new Thrower());

        var result = Assertions.assertDoesNotThrow(() -> new LispAnd().apply(null, args));
        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), false);
    }

    @Test
    public void testOrLaziness() {
        List<Expression> args = List.of(
                new LispObject(false),
                new LispObject(false),
                new LispObject(true),
                new Thrower(), new Thrower(), new Thrower());

        var result = Assertions.assertDoesNotThrow(() -> new LispOr().apply(null, args));
        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Boolean);
        Assertions.assertEquals(((LispObject) result).self(), true);
    }

    @Test
    public void testAndLaziness_wrong() {
        List<Expression> args = List.of(
                new LispObject(true),
                new LispObject(true),
                new Thrower(),
                new LispObject(false),
                new Thrower(), new Thrower(), new Thrower());

        var result = Assertions.assertThrows(RuntimeException.class,
                () -> new LispAnd().apply(null, args),
                "Should not be invoked");
    }

    @Test
    public void testOrLaziness_wrong() {
        List<Expression> args = List.of(
                new LispObject(false),
                new LispObject(false),
                new Thrower(),
                new LispObject(true),
                new Thrower(), new Thrower(), new Thrower());

        var result = Assertions.assertThrows(RuntimeException.class,
                () -> new LispOr().apply(null, args),
                "Should not be invoked");
    }
}
