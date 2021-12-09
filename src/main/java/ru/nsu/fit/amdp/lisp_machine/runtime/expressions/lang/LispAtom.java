package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.Derefable;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispQuote;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LispAtom implements Expression, Derefable {
    AtomicReference<Expression> value;
    public LispAtom(Expression initialValue) {
        value = new AtomicReference<>(initialValue);
    }

    @Override
    public Expression deref() {
        return value.get();
    }

    public Expression assign(Expression value) {
        this.value.set(value);
        return value;
    }

    public Expression modify(LispBaseFunction transform) {
        return this.value.updateAndGet((val) ->
                transform.apply(null, List.of(new LispQuotedExpression(val)))
        );
    }
}
