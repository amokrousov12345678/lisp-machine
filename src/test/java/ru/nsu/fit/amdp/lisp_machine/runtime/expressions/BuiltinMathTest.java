package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.LispContext;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;


public class BuiltinMathTest {

    static Context getArithmeticsContext(){
        Context context = new LispContext();
        context.define(new LispIdentifier("+"), new Add());
        context.define(new LispIdentifier("-"), new Sub());
        context.define(new LispIdentifier("*"), new Mult());
        context.define(new LispIdentifier("/"), new Div());
        context.define(new LispIdentifier("mod"), new Mod());
        return context;
    }

    @Test
    public void plusInteger_correct() throws ParseException {
        String expr = "(+ 2 3 4)";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(9));
    }

    @Test
    public void plusFloat_correct() throws ParseException {
        String exprWithFloats = "(+ 2 3 4.0)";
        var listExprs = TestParser.parseLispStatement(exprWithFloats);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Float);
        Assertions.assertEquals(result, new LispObject(9.0f));
    }

    @Test
    public void multInteger_correct() throws ParseException {
        String expr = "(* 2 3 4)";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(24));
    }

    @Test
    public void multFloat_correct() throws ParseException {
        String exprWithFloats = "(* 2 3 4.0)";
        var listExprs = TestParser.parseLispStatement(exprWithFloats);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Float);
        Assertions.assertEquals(result, new LispObject(24.0f));
    }

    @Test
    public void divInteger_correct() throws ParseException {
        String expr = "(/ 5 2)";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(2));
    }

    @Test
    public void divFloat_correct() throws ParseException {
        String exprWithFloats = "(/ 2 4.0)";
        var listExprs = TestParser.parseLispStatement(exprWithFloats);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Float);
        Assertions.assertEquals(result, new LispObject(0.5f));
    }

    @Test
    public void minusInteger_correct() throws ParseException {
        String expr = "(- 5 2)";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(3));
    }

    @Test
    public void minusFloat_correct() throws ParseException {
        String exprWithFloats = "(- 2 4.0)";
        var listExprs = TestParser.parseLispStatement(exprWithFloats);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Float);
        Assertions.assertEquals(result, new LispObject(-2.0f));
    }

    @Test
    public void minusUnary_correct() throws ParseException {
        String exprWithFloats = "(- 2)";
        var listExprs = TestParser.parseLispStatement(exprWithFloats);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(-2));
    }

    @Test
    public void modInteger_correct() throws ParseException {
        String expr = "(mod 7 5)";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(getArithmeticsContext());

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Integer);
        Assertions.assertEquals(result, new LispObject(2));
    }

    @Test
    public void modInteger_incorrect() throws ParseException {
        String expr = "(mod 7 5 2)";
        var listExprs = TestParser.parseLispStatement(expr);

        var thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> listExprs.get(0).evaluate(getArithmeticsContext()),
                "Incorrect amount of args for mod");
    }
}
