package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading.LispStartedFutureExpression;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Future implementation.
 */
public class LispFuture implements Expression {

    /**
     * Create instance of {@link LispStartedFutureExpression} with
     * stored promise to function provided in {@code args[0]} invocation.
     *
     * @param context execution context
     * @param args    function without arguments to be invoked asynchronously.
     * @return {@link LispStartedFutureExpression} with stored promise to the result
     *         of {@code args[0]} function invocation.
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too few args for future");
        }
        Expression futureFunction = args.get(0).evaluate(context);
        var promise = CompletableFuture.supplyAsync(() -> futureFunction.apply(context,  List.of()));
        return new LispStartedFutureExpression(promise);
    }
}
