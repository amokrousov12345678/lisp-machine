package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispQuotedExpression;

import java.util.List;

public class LispMacroExpandOnce extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();

        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid count argument for macroexpand");
        }
        List<Expression> argList = ((LispExecutableList) args.get(0)).asList();
        Expression firstArg = argList.get(0).evaluate(context);
        if (!(firstArg instanceof LispMacroExpression)) {
            return args.get(0);
        }
        argList.remove(0);
        return ((LispMacroExpression) firstArg).expand(context, argList);
    }
}