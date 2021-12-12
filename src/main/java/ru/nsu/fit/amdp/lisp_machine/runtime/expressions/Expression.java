package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;

import java.util.List;
import java.util.Objects;

/**
 * A common interface for lisp
 * commands object representation.
 */
public interface Expression {

    /**
     * Evaluate expression in provided context
     * @param context execution context (map from variable names to instances of Expression)
     * @return result of evaluation of current expression in provided context
     * By default returns expression itself
     */
    default Expression evaluate(Context context) {
        return this;
    }

    /**
     * Apply expression to provided list of arguments in provided context
     * @param context execution context
     * @param args list of arguments
     * @return result of application of current expression in provided context to provided arguments.
     * If expression is not applicable or has wrong arguments throws NotCallableObjectError.
     */
    default Expression apply(Context context, List<Expression> args) {
        throw new NotCallableObjectError(this.toString());
    }

    default boolean equals(Expression other) {
        return Objects.equals(this, other);
    }

    /**
     * @return value of expression as bool.
     * By default all expressions except false equals true.
     */
    default boolean asBool() {
        return true;
    }
}
