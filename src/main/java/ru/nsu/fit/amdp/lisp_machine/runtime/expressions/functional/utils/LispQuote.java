package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

public class LispQuote implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid arg count");
        }

        return args.get(0);
    }

}