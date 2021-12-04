package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

import java.util.List;

public class LispOr implements Expression{
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for OR");

        var result = false;

        for (var arg: args) {
            result = arg.evaluate(context).asBool();
            if (result)
                break;
        }

        return new LispObject(result);
    }

    @Override
    public String toString() {
        return "or";
    }
}
