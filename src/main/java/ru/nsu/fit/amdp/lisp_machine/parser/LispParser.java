package ru.nsu.fit.amdp.lisp_machine.parser;

import ru.nsu.fit.amdp.lisp_machine.grammar.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Parser for provided program implemented in own dialect of lisp.
 * For more information about grammar see LispProgram.jjt
 */
public class LispParser {

    /**
     * @param node parsed identifier
     * @return LispIdentifier
     */
    static LispIdentifier transformASTNode(ASTIdentifier node) {
        return new LispIdentifier((String) node.jjtGetValue());
    }

    /**
     * @param node parsed node
     * @return parsed value wrapped in LispObject
     */
    static LispObject transformASTNode(SimpleNode node) {
        return new LispObject(node.jjtGetValue());
    }

    /**
     * Transforms ASTList node into LispExecutableList
     * @param list parsed ASTList node
     * @return LispExecutableList
     */
    static LispExecutableList transformASTNode(ASTList list) {
        LinkedList<Expression> result = new LinkedList<>();
        for (int i = 0; i < list.jjtGetNumChildren(); i++) {
            SimpleNode listItem = (SimpleNode) list.jjtGetChild(i);
            if (listItem instanceof ASTIdentifier) {
                result.add(new LispIdentifier((String) listItem.jjtGetValue()));
            } else if (listItem instanceof ASTList) {
                result.add(transformASTNode((ASTList) listItem));
            } else {
                result.add(new LispObject(listItem.jjtGetValue()));
            }
        }
        return new LispExecutableList(result);
    }

    /**
     * Transforms AST node into List of expressions
     * @param lispExpressions parsed AST
     * @return List of parsed expressions
     */
    static List<Expression> transformASTNode(ASTLispExpressions lispExpressions) {
        List<Expression> result = new ArrayList<>();
        for (int i = 0; i < lispExpressions.jjtGetNumChildren(); i++) {
            Node node = lispExpressions.jjtGetChild(i);
            if (node instanceof ASTList) {
                result.add(transformASTNode((ASTList) node));
            } else if (node instanceof ASTIdentifier) {
                result.add(transformASTNode((ASTIdentifier) node));
            } else {
                result.add(transformASTNode((SimpleNode) node));
            }
        }
        return result;
    }

    /**
     * @param inputStream InputStream to read program from
     * @return List of parsed expressions
     * @throws ParseException on syntax error
     */
    public static List<Expression> parseLispProgram(InputStream inputStream) throws ParseException {
        LispStatement parser = new LispStatement(inputStream);
        ASTLispExpressions lispExpressions = parser.LispExpressions();
        return transformASTNode(lispExpressions);
    }

    /**
     * @param input String with valid lisp program
     * @return List of parsed expressions
     * @throws ParseException on syntax error
     */
    public static List<Expression> parseLispProgram(String input) throws ParseException {
        return parseLispProgram(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }
}
