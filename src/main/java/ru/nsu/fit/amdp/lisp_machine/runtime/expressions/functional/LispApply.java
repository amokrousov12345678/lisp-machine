package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.List;

/**
 * Apply implementation
 */
public class LispApply implements Expression {

    /**
     * Apply args[0] to arguments provided in args[1].
     *
     * @param context execution context
     * @param args    list of arguments of length 2<ul>
     *                      <li>args[0] should be an Expression which evaluates to a function</li>
     *                      <li>args[1] list of arguments to apply provided function on</li></ul>
     *
     * @return result of args[0] application to args[1]
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments for apply");
        }

        Expression function = args.get(0).evaluate(context);
        Expression arg = args.get(1).evaluate(context);
        List<Expression> arglist;

        if (arg instanceof LispExecutableList) {
            arglist = ((LispExecutableList) arg).asList();
        } else {
            arglist = List.of(arg.evaluate(context));
        }

        return function.apply(context, arglist);
    }
}
