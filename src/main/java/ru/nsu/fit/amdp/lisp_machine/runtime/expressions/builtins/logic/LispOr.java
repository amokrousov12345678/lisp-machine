package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

/**
 * Or implementation (lazy)
 */
public class LispOr implements Expression{

    /**
     * Lazily evaluates logic OR of provided args.
     * As soon as one of them evaluates to true,
     * true is returned.
     *
     * @param context execution context
     * @param args    list of arguments
     * @return logic OR of provided args
     */
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
