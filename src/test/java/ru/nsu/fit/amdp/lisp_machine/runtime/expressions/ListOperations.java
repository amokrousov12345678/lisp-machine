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
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

import java.util.List;

public class ListOperations {
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

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof LispPersistentList);

        List<Expression> expected = List.of(
            new LispObject(1),
            new LispObject(2),
            new LispObject(3),
            new LispObject(4),
            new LispObject(5));

        Assertions.assertEquals(((LispPersistentList) ((LispObject) result).self()).asList(),
                expected);
    }


    @Test
    public void createNestedListsTest() throws ParseException {
        var listExprs = TestParser.parseLispStatement("(list 1 (list 2 3 (list 4)) 5)");

        var operation = listExprs.get(0);
        var result = operation.evaluate(listOperationsContest);

        Assertions.assertTrue(result instanceof LispObject);
        Assertions.assertTrue(((LispObject) result).self() instanceof LispPersistentList);

        List<Expression> expected = List.of(
            new LispObject(1),
            new LispObject(new LispPersistentList(
                    List.of(
                            new LispObject(2),
                            new LispObject(3),
                            new LispObject(new LispPersistentList(
                                    List.of(
                                            new LispObject(4)
                                    )
                            ))
                    )
            )),
            new LispObject(5)
        );

        Assertions.assertEquals(((LispPersistentList) ((LispObject) result).self()).asList(),
                expected);
    }

}
