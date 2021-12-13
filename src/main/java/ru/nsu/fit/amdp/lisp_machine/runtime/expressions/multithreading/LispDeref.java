package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * Deref implementation
 */
public class LispDeref extends LispBaseFunction {
    /**
     * Dereferences provided expression.
     *
     * @param args expression to dereference.
     * @return result of expression provided in {@code args[0]} dereference.
     */
    @Override
    public Expression execute(List<Expression> args) {
        Derefable derefable = (Derefable) args.get(0);
        return derefable.deref();
    }
}