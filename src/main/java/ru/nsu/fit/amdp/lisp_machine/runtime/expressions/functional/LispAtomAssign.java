package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispAtom;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class LispAtomAssign extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Too few args for atom");
        }

        LispAtom lispAtom = (LispAtom) args.remove(0);
        Expression value = args.remove(0);
        return lispAtom.assign(value);
    }
}