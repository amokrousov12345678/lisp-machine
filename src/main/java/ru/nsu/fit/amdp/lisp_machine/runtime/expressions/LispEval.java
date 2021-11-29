package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;

public class LispEval extends LispBaseFunction {
    @Override
    public Expression execute() {
        var args = getArgs();
        var context = getContext();
        return args.remove(0).evaluate(context).evaluate(context);
    }
}