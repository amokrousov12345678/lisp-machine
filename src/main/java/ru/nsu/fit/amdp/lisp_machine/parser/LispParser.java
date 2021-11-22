package ru.nsu.fit.amdp.lisp_machine.parser;

import ru.nsu.fit.amdp.lisp_machine.grammar.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LispParser {
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

    static List<LispExecutableList> transformASTNode(ASTLispExpressions lispExpressions) {
        List<LispExecutableList> result = new ArrayList<>();
        for (int i = 0; i < lispExpressions.jjtGetNumChildren(); i++) {
            ASTList list = (ASTList) lispExpressions.jjtGetChild(i);
            result.add(transformASTNode(list));
        }
        return result;
    }

    public static List<LispExecutableList> parseLispProgram(InputStream inputStream) throws ParseException {
        LispStatement parser = new LispStatement(inputStream);
        ASTLispExpressions lispExpressions = parser.LispExpressions();
        return transformASTNode(lispExpressions);
    }
}
