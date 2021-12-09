package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispAtom;

import java.util.List;

public class LispAtomModify extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Too few args");
        }

        LispAtom lispAtom = (LispAtom) args.remove(0);
        LispBaseFunction value = (LispBaseFunction) args.remove(0);
        return lispAtom.modify(value);
    }
}