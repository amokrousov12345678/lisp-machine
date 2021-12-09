package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispMacroExpression;

import java.util.List;

public class LispMacro implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too few args for macro");
        }

        return new LispMacroExpression(args.get(0).evaluate(context));
    }
}