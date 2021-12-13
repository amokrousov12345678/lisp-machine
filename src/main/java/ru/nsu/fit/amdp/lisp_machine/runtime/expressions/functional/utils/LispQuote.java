package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

/**
 * quote implementation
 */
public class LispQuote implements Expression {

    /**
     * Propagate expression without its evaluation
     *
     * @param context execution context
     * @param args    expression to be quoted
     * @return expression without its evaluation
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid arg count");
        }

        return args.get(0);
    }

}
