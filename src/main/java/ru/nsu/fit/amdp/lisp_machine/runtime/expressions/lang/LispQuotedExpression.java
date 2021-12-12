package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.UnknownIdentifierError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Objects;

/**
 * Quote support for macros and deferred evaluation
 */
public class LispQuotedExpression implements Expression {

    private final Expression expression;

    public LispQuotedExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * @param context execution context (map from variable names to instances of Expression)
     * @return wrapped expression without it's evaluation
     */
    @Override
    public Expression evaluate(Context context) {
        return expression;
    }
}
