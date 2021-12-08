package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LispFuture implements Expression {

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
