package ru.nsu.fit.amdp.lisp_machine.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.test_utils.TestParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class LispParserTest {
    @Test
    @SuppressWarnings("unchecked")
    public void parseListProgram_correct() throws ParseException {
        String expr = "(list) (if (= val 0) (list 0.0) (list \"abacaba\"))";
        var listExprs = TestParser.parseLispStatement(expr);

        Assertions.assertEquals(listExprs.size(), 2);
        Assertions.assertEquals(listExprs.get(0).size(), 1);
        Assertions.assertEquals(listExprs.get(0).get(0), new LispIdentifier("list"));
        Assertions.assertEquals(listExprs.get(1).size(), 4);
        Assertions.assertEquals(listExprs.get(1).get(0), new LispIdentifier("if"));

        Assertions.assertTrue(listExprs.get(1).get(1) instanceof LispExecutableList);
        Assertions.assertTrue(listExprs.get(1).get(2) instanceof LispExecutableList);
        Assertions.assertTrue(listExprs.get(1).get(3) instanceof LispExecutableList);

        List<Expression> arg2 = ((LispExecutableList) listExprs.get(1).get(1)).asList();
        List<Expression> arg3 = ((LispExecutableList) listExprs.get(1).get(2)).asList();
        List<Expression> arg4 = ((LispExecutableList) listExprs.get(1).get(3)).asList();

        Assertions.assertEquals(arg2.size(), 3);
        Assertions.assertEquals(arg2.get(0), new LispIdentifier("="));
        Assertions.assertEquals(arg2.get(1), new LispIdentifier("val"));
        Assertions.assertEquals(arg2.get(2), new LispObject(0));

        Assertions.assertEquals(arg3.size(), 2);
        Assertions.assertEquals(arg3.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg3.get(1), new LispObject(0.0f));

        Assertions.assertEquals(arg4.size(), 2);
        Assertions.assertEquals(arg4.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg4.get(1), new LispObject("abacaba"));
    }
}
