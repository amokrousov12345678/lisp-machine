package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;

public class LispIf implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 3) {
            throw new IllegalArgumentException("Incorrect number arguments for if");
        }

        var cond = args.get(0).evaluate(context);

        if (!(cond instanceof LispObject) || !(((LispObject) cond).self() instanceof Boolean)) {
            throw new IllegalArgumentException("Predicate for if MUST be boolean");
        }

        boolean condValue = (Boolean) ((LispObject) cond).self();

        if (condValue) {
            return args.get(1).evaluate(context);
        } else {
            return args.get(2).evaluate(context);
        }
    }

}
