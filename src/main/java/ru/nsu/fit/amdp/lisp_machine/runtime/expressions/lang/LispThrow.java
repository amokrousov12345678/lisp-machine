package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;


import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.LispException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * Throw exception
 */
public class LispThrow extends LispBaseFunction {

    /**
     * @param args list of arguments of len 1. An exception wrapped in {@link LispObject} should be stored in args[0].
     * @return Does not return. Wraps exception provided in args[0] into LispException and throws it.
     */
    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();
        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid count args for throw");
        }

        var exception = args.remove(0);


        throw new LispException((Throwable) ((LispObject) exception).self());
    }
}
