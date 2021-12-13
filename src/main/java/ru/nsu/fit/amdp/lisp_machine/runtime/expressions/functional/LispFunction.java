package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils.LispRecurHolder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Class to represent user-defined function.
 */
public class LispFunction extends LispBaseFunction {
    private final Context closure;
    private final List<Expression> body;
    private final List<LispIdentifier> argnames;
    private final boolean isVararg;

    public Context getClosure() {return closure;}

    /**
     * Create user-defined function without variadic args.
     *
     * @param context context to be stored in function as closure.
     * @param body expressions, which are going to be
     *             evaluated on function call.
     *             Result of the last expression in body
     *             is returned as the result of function call.
     * @param argnames names of function arguments
     */
    public LispFunction(Context context,
                        List<Expression> body,
                        List<LispIdentifier> argnames) {
        this(context, body, argnames, false);
    }

    /**
     * Create user-defined function.
     *
     * @param context context to be stored in function as closure.
     * @param body expressions, which are going to be
     *             evaluated on function call.
     *             Result of the last expression in body
     *             is returned as the result of function call.
     * @param argnames names of function arguments. If function
     *                 has variadic arguments, this is always
     *                 the last argument.
     * @param isVararg true if function has variadic arguments, otherwise false.
     */
    public LispFunction(Context context,
                        List<Expression> body,
                        List<LispIdentifier> argnames,
                        boolean isVararg) {
        this.closure = context.makeCopy();
        this.body = body;
        this.argnames = argnames;
        this.isVararg = isVararg;
    }

    /**
     * Perform single function invocation.
     *
     * @param args list of evaluated arguments.
     * @return result of the single function application
     *         to provided arguments.
     */
    private Expression executeOnce(List<Expression> args) {
        if (!isVararg && (args.size() != argnames.size()))
            throw new RuntimeException("Wrong amount of variables!!!");

        if (isVararg && (args.size() < argnames.size()-1))
            throw new RuntimeException("Wrong amount of variables!!!");

        var callClojure = closure.makeCopy();
        IntStream.range(0, isVararg ? (argnames.size() - 1) : argnames.size())
                .forEach(i -> callClojure.define(argnames.get(i), args.remove(0)));

        if (isVararg) {
            var lastArgname = argnames.get(argnames.size() - 1);
            callClojure.define(lastArgname,  new LispExecutableList(args));
        }

        return new LinkedList<>(body).stream()
                .map(expr -> expr.evaluate(callClojure))
                .reduce(null, (a,b) -> b);
    }

    /**
     * Apply function to provided arguments.
     *
     * @param args list of evaluated arguments
     * @return result of function application to provided arguments.
     */
    @Override
    public Expression execute(List<Expression> args) {
        Expression result = null;
        while (true) {
            result = executeOnce(args);

            if (!(result instanceof LispRecurHolder))
                break;

            args = ((LispRecurHolder) result).getInmate();
        }

        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispFunction that = (LispFunction) other;
        return Objects.equals(this.argnames, that.argnames)
                && Objects.equals(this.body, that.body)
                && Objects.equals(this.closure, that.closure)
                && (this.isVararg == that.isVararg);
    }
}
