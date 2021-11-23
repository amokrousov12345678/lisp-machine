package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;

public class LispDefine implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 2) {
            throw new IllegalArgumentException("Too many args for def");
        }

        if (!(args.get(0) instanceof LispIdentifier)) {
            throw new IllegalArgumentException("No valid identifier provided");
        }

        var id = (LispIdentifier) args.get(0);

        context.define(id, args.get(1).evaluate(context));

        return this;
    }

}
