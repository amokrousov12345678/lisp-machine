package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * <p>recur implementation for tail-recursion optimization support</p>
 * <p>The same as in clojure 'recur' keyword should appear only as
 * the last call.</p>
 */
public class LispRecur extends LispBaseFunction {

    /**
     * Evaluates the args in order, then, in parallel, rebinds the bindings of
     * the recursion point to the values of the args.
     *
     * @param args list of evaluated arguments to be used as
     *             function arguments one more time
     * @return  {@link LispRecurHolder} object which stores evaluated args
     *          and propagates them to the next recursive call of the same
     *          function
     */
    @Override
    public Expression execute(List<Expression> args) {
        return new LispRecurHolder(args);
    }

}
