package ru.nsu.fit.amdp.lisp_machine.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

import java.util.List;

public class LispParserTest {
    @Test
    @SuppressWarnings("unchecked")
    public void parseListProgram_correct() throws ParseException {
        String expr = "(list) (if (= val 0) (list 0.0) (list \"abacaba\")) 2 \"XD\" x";
        var listExprs = TestParser.parseLispStatement(expr);

        Assertions.assertEquals(listExprs.size(), 5);

        Assertions.assertTrue(listExprs.get(0) instanceof LispExecutableList);
        Assertions.assertTrue(listExprs.get(1) instanceof LispExecutableList);
        Assertions.assertTrue(listExprs.get(2) instanceof LispObject);
        Assertions.assertTrue(listExprs.get(3) instanceof LispObject);
        Assertions.assertTrue(listExprs.get(4) instanceof LispIdentifier);

        var statement1 = (LispExecutableList) listExprs.get(0);
        var statement2 = (LispExecutableList) listExprs.get(1);
        var statement3 = (LispObject) listExprs.get(2);
        var statement4 = (LispObject) listExprs.get(3);
        var statement5 = (LispIdentifier) listExprs.get(4);

        Assertions.assertEquals(statement1.size(), 1);
        Assertions.assertEquals(statement1.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(statement2.size(), 4);
        Assertions.assertEquals(statement2.get(0), new LispIdentifier("if"));

        Assertions.assertTrue(statement2.get(1) instanceof LispExecutableList);
        Assertions.assertTrue(statement2.get(2) instanceof LispExecutableList);
        Assertions.assertTrue(statement2.get(3) instanceof LispExecutableList);

        List<Expression> arg2 = ((LispExecutableList) statement2.get(1)).asList();
        List<Expression> arg3 = ((LispExecutableList) statement2.get(2)).asList();
        List<Expression> arg4 = ((LispExecutableList) statement2.get(3)).asList();

        Assertions.assertEquals(arg2.size(), 3);
        Assertions.assertEquals(arg2.get(0), new LispIdentifier("="));
        Assertions.assertEquals(arg2.get(1), new LispIdentifier("val"));
        Assertions.assertEquals(arg2.get(2), new LispObject(0L));

        Assertions.assertEquals(arg3.size(), 2);
        Assertions.assertEquals(arg3.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg3.get(1), new LispObject(0.0));

        Assertions.assertEquals(arg4.size(), 2);
        Assertions.assertEquals(arg4.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg4.get(1), new LispObject("abacaba"));

        Assertions.assertEquals(statement3, new LispObject(2L));
        Assertions.assertEquals(statement4, new LispObject("XD"));
        Assertions.assertEquals(statement5, new LispIdentifier("x"));
    }
}
