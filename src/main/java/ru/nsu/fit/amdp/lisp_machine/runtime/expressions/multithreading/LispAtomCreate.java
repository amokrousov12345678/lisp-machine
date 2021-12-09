package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

public class LispAtomCreate extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too few args");
        }
        return new LispAtom(args.get(0));
    }
}