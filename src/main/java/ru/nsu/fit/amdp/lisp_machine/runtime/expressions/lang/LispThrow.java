package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;


import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.LispException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;
public class LispThrow extends LispBaseFunction {

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
