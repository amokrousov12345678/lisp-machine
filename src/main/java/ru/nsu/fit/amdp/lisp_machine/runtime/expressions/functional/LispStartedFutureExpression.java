package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LispStartedFutureExpression implements Expression, Derefable {
    private CompletableFuture<Expression> promise;

    public LispStartedFutureExpression(CompletableFuture<Expression> promise) {this.promise = promise;}

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
