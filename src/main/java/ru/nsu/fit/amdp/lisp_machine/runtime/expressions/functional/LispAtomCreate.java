package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispAtom;

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