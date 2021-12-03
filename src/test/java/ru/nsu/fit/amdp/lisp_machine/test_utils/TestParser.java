package ru.nsu.fit.amdp.lisp_machine.test_utils;

import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.parser.LispParser;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;

import java.io.ByteArrayInputStream;
import java.util.List;

public class TestParser {
    public static List<LispExecutableList> parseLispStatement(String statement) throws ParseException {
        var istream = new ByteArrayInputStream(statement.getBytes());
        return LispParser.parseLispProgram(istream);
    }
}
