package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;

import java.util.List;

public class LispMacroExpandOnce implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 1) {
            throw new IllegalArgumentException("Too few args for macroexpand");
        }
        Expression expandedExpression = args.remove(0).evaluate(context);
        if (!(expandedExpression instanceof LispMacroExpression)) {
            throw new IllegalArgumentException("macroexpand-1 must be applied to macro");
        }
        LispMacroExpression macroExpression = (LispMacroExpression) expandedExpression;
        return macroExpression.expand(context, args);
    }
}