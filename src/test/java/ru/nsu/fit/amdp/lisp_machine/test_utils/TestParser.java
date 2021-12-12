package ru.nsu.fit.amdp.lisp_machine.test_utils;

import ru.nsu.fit.amdp.lisp_machine.grammar.ParseException;
import ru.nsu.fit.amdp.lisp_machine.parser.LispParser;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Parser to be used in unit tests
 */
public class TestParser {
    /**
     * @param statement valid list statement (could be program)
     * @return List of parsed expressions
     * @throws ParseException on syntax error
     */
    public static List<Expression> parseLispStatement(String statement) throws ParseException {
        var istream = new ByteArrayInputStream(statement.getBytes());
        return LispParser.parseLispProgram(istream);
    }
}
