package ru.nsu.fit.amdp.lisp_machine.parser;

import ru.nsu.fit.amdp.lisp_machine.grammar.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LispParser {
    static List<Object> transformASTNode(ASTList list) {
        LinkedList<Object> result = new LinkedList<>();
        for (int i=0;i<list.jjtGetNumChildren();i++) {
            SimpleNode listItem = (SimpleNode) list.jjtGetChild(i);
            if (listItem instanceof ASTIdentifier) {
                result.add(new LispIdentifier((String) listItem.jjtGetValue()));
            } else if (listItem instanceof ASTList) {
                result.add(transformASTNode((ASTList) listItem));
            } else {
                result.add(listItem.jjtGetValue());
            }
        }
        return result;
    }

    static List<List<Object>> transformASTNode(ASTLispExpressions lispExpressions) {
        List<List<Object>> result = new ArrayList<>();
        for (int i=0;i<lispExpressions.jjtGetNumChildren();i++) {
            ASTList list = (ASTList) lispExpressions.jjtGetChild(i);
            result.add(transformASTNode(list));
        }
        return result;
    }

    public static List<List<Object>> parseLispProgram(InputStream inputStream) throws ParseException {
        LispStatement parser = new LispStatement(inputStream);
        ASTLispExpressions lispExpressions = parser.LispExpressions();
        return transformASTNode(lispExpressions);
    }
}
