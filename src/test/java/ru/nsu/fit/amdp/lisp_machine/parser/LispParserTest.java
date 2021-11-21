package ru.nsu.fit.amdp.lisp_machine.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class LispParserTest {
    @Test
    @SuppressWarnings("unchecked")
    public void parseListProgram_correct() throws ParseException {
        String expr = "(list) (if (= val 0) (list 0.0) (list \"abacaba\"))";
        InputStream is = new ByteArrayInputStream(expr.getBytes());
        List<List<Object>> listExprs = LispParser.parseLispProgram(is);

        Assertions.assertEquals(listExprs.size(), 2);
        Assertions.assertEquals(listExprs.get(0).size(), 1);
        Assertions.assertEquals(listExprs.get(0).get(0), new LispIdentifier("list"));
        Assertions.assertEquals(listExprs.get(1).size(), 4);
        Assertions.assertEquals(listExprs.get(1).get(0), new LispIdentifier("if"));

        Assertions.assertTrue(listExprs.get(1).get(1) instanceof List);
        Assertions.assertTrue(listExprs.get(1).get(2) instanceof List);
        Assertions.assertTrue(listExprs.get(1).get(3) instanceof List);
        List<Object> arg2 = (List<Object>) listExprs.get(1).get(1);
        List<Object> arg3 = (List<Object>) listExprs.get(1).get(2);
        List<Object> arg4 = (List<Object>) listExprs.get(1).get(3);

        Assertions.assertEquals(arg2.size(), 3);
        Assertions.assertEquals(arg2.get(0), new LispIdentifier("="));
        Assertions.assertEquals(arg2.get(1), new LispIdentifier("val"));
        Assertions.assertEquals(arg2.get(2), 0);

        Assertions.assertEquals(arg3.size(), 2);
        Assertions.assertEquals(arg3.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg3.get(1), 0.0f);

        Assertions.assertEquals(arg4.size(), 2);
        Assertions.assertEquals(arg4.get(0), new LispIdentifier("list"));
        Assertions.assertEquals(arg4.get(1), "abacaba");
    }
}
