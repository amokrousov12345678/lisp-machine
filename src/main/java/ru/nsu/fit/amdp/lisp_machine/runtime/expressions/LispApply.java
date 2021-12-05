package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;

public class LispApply implements Expression{
    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 2) {
            throw new IllegalArgumentException("Incorrect number arguments for apply");
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
