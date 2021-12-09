package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.CreateList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListConcat;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListFirst;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListRest;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

import java.util.List;

public class ListOperationsTest {
    static final Context listOperationsContest = BuiltinMathTest.getArithmeticsContext();

    @BeforeAll
    static void fillContext() {
        listOperationsContest.define(new LispIdentifier("list"), new CreateList());
        listOperationsContest.define(new LispIdentifier("first"), new ListFirst());
        listOperationsContest.define(new LispIdentifier("rest"), new ListRest());
        listOperationsContest.define(new LispIdentifier("concat"), new ListConcat());
    }

    @Test
    public void createListTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(list 1 2 3 4 5)");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of(
            new LispObject(1L),
            new LispObject(2L),
            new LispObject(3L),
            new LispObject(4L),
            new LispObject(5L));

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }


    @Test
    public void createNestedListsTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(list 1 (list 2 3 (list 4)) 5)");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of(
            new LispObject(1L),
            new LispExecutableList(
                    List.of(
                            new LispObject(2L),
                            new LispObject(3L),
                            new LispExecutableList(
                                    List.of(
                                            new LispObject(4L)
                                    )
                            )
                    )
            ),
            new LispObject(5L)
        );

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void getFirstTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(first (list 1 (list 2 3 (list 4)) 5))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof Long);
        Assertions.assertEquals(((LispObject) result).self(), 1L);
    }

    @Test
    public void getFirstEmptyListTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(first (list))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of();

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void getRestTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(rest (list 1 (list 2 3 (list 4)) 5))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of(
                new LispExecutableList(
                        List.of(
                                new LispObject(2L),
                                new LispObject(3L),
                                new LispExecutableList(
                                        List.of(
                                                new LispObject(4L)
                                        )
                                )
                        )
                ),
                new LispObject(5L)
        );

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void getRestTwoElementListTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(rest (list 1 2))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of(new LispObject(2L));

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void getRestSingleElementListTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(rest (list 1))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of();

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void getRestEmptyListTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(rest (list))");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispExecutableList);

        List<Expression> expected = List.of();

        Assertions.assertEquals(((LispExecutableList) result).asList(),
                expected);
    }

    @Test
    public void complexRestFirstTest() throws ParseException {
        String expr = "(first (first (rest (rest (first (rest (list 1 (list 2 3 (list \"G0D\")) 5)))))))";
        var listExprs = TestParser.parseLispStatement(expr);

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof String);
        Assertions.assertEquals(((LispObject) result).self(), "G0D");
    }

    @Test
    public void concatListsTest() throws ParseException {
        String expr = "(concat (list 1 2 3) (list 2) (list 3 4) (list (list \"._.\" \":)\")" +
                " (list \":D\" list) (list (+ 3 4 2) 1)))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(listOperationsContest);

        String expectedExpr = "(list 1 2 3 2 3 4 (list \"._.\" \":)\") (list \":D\" list) (list 9 1))";
        var expected = TestParser.parseLispStatement(expectedExpr)
                .get(0).evaluate(listOperationsContest);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void concatEmptyListsTest() throws ParseException {
        String expr = "(concat (list) (list) (list))";
        var listExprs = TestParser.parseLispStatement(expr);

        var result = listExprs.get(0).evaluate(listOperationsContest);

        String expectedExpr = "(list)";
        var expected = TestParser.parseLispStatement(expectedExpr)
                .get(0).evaluate(listOperationsContest);

        Assertions.assertEquals(expected, result);
    }
}
