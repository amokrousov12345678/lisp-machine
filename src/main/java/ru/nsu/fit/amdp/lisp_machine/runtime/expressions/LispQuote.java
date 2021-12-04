package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.List;

public class LispQuote implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid arg count");
        }

        var arg = args.get(0);
        if (arg instanceof LispExecutableList) {
            return new LispObject(new LispPersistentList((LispExecutableList) arg));
        }
        return new LispQuotedExpression(args.get(0));
    }

}