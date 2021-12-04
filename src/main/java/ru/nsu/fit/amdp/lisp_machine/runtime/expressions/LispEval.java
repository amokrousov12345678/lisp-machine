package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import java.util.List;

public class LispEval extends LispBaseFunction {
    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();
        var arg = args.remove(0);
        return arg.evaluate(context);
    }
}