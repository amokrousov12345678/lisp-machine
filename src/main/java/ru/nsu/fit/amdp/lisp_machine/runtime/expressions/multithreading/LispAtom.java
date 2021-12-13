package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispQuotedExpression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Atomic reference implementation
 */
public class LispAtom implements Expression, Derefable {
    AtomicReference<Expression> value;
    public LispAtom(Expression initialValue) {
        value = new AtomicReference<>(initialValue);
    }

    /**
     * @return dereferenced value.
     */
    @Override
    public Expression deref() {
        return value.get();
    }

    /**
     * Assign {@code value} to stored object.
     *
     * @param value value to assign.
     * @return {@code value}.
     */
    public Expression assign(Expression value) {
        this.value.set(value);
        return value;
    }

    /**
     * Update stored value using transform and get updated value.
     *
     * @param transform transformer function.
     * @return result of transform application to stored value.
     */
    public Expression modify(LispBaseFunction transform) {
        return this.value.updateAndGet((val) ->
                transform.apply(null, List.of(new LispQuotedExpression(val)))
        );
    }
}
