package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

public class LispDeref extends LispBaseFunction {
    @Override
    public Expression execute(List<Expression> args) {
        Derefable derefable = (Derefable) args.get(0);
        return derefable.deref();
    }
}