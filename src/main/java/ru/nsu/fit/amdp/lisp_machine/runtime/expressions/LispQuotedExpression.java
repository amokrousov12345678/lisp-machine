package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.UnknownIdentifierError;

import java.util.Objects;

public class LispQuotedExpression implements Expression{

    private Expression expression;

    public LispQuotedExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Expression evaluate(Context context) {
        return expression;
    }
}

