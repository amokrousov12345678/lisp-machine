package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Common base class for functions.
 */
public abstract class LispBaseFunction implements Expression, Runnable {

    private Context context;

    /**
     * Evaluates all provided arguments and propagates them
     * to {@link #execute(List)}
     *
     * @param context execution context
     * @param args    list of arguments
     * @return result of function application to provided arguments
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        var argsValues = args.stream()
                .map(arg -> arg.evaluate(context))
                .collect(Collectors.toList());
        this.context = context;
        return execute(argsValues);
    }

    /**
     * All functions are evaluated to themselves.
     *
     * @param context execution context (map from variable names to instances of Expression)
     * @return function itself
     */
    @Override
    public Expression evaluate(Context context){
        return this;
    }

    /**
     * Override this method to implement own custom function logic
     *
     * @param args list of evaluated arguments
     * @return result of function call with provided arguments
     */
    public abstract Expression execute(List<Expression> args);

    /**
     * Get the last cached context
     *
     * @return cached context
     */
    public Context getContext() { return context; }

    public void run() {
        apply(null, List.of());
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispBaseFunction that = (LispBaseFunction) other;
        return Objects.equals(this.context, that.context);
    }
}
