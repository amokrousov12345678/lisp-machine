package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class LispMacro implements Expression{

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too few args for macro");
        }

        return new LispMacroExpression(args.get(0).evaluate(context));
    }
}