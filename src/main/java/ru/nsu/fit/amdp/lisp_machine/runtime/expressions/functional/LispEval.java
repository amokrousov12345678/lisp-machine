package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

/**
 * eval implementation
 */
public class LispEval extends LispBaseFunction {

    /**
     * Force evaluation of provided expression.
     *
     * @param args list of evaluated arguments
     * @return result of args[0] evaluation
     */
    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();
        var arg = args.remove(0);
        return arg.evaluate(context);
    }
}