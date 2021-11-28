package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;

import java.util.List;
import java.util.Objects;

public interface Expression {

    default Expression evaluate(Context context) {
        return this;
    }

    default Expression apply(Context context, List<Expression> args) {
        throw new NotCallableObjectError(this.toString());
    }

    default boolean equals(Expression other) {
        return Objects.equals(this, other);
    };
}
