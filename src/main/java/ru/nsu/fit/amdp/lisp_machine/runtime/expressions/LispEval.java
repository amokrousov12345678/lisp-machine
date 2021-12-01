package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.List;

public class LispEval extends LispBaseFunction {
    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();
        var arg = args.remove(0);
        if (arg instanceof LispObject) {
            var object = ((LispObject)arg).self();
            if (object instanceof LispPersistentList) {
                return new LispExecutableList((LispPersistentList) object).evaluate(context);
            }
        }
        if (arg instanceof LispQuotedExpression) {
            return arg.evaluate(context).evaluate(context);
        }
        throw new UnsupportedOperationException("Can't eval of non-list and non-quoted expresion");
    }
}