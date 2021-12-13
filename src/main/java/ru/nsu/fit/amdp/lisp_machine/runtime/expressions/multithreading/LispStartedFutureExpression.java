package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Promise implementation
 */
public class LispStartedFutureExpression implements Expression, Derefable {
    private final CompletableFuture<Expression> promise;

    public LispStartedFutureExpression(CompletableFuture<Expression> promise) {this.promise = promise;}

    /**
     * Attempt to get value stored in wrapped promise.
     *
     * @return evaluated expression from promise.
     */
    @Override
    public Expression deref() {
        try {
            return promise.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
