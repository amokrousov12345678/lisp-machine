package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * Atom modification
 */
public class LispAtomModify extends LispBaseFunction {

    /**
     * Modifies {@link LispAtom} instance stored in {@code args[0]}
     * using function stored in {@code args[1]}.
     *
     * @param args list of arguments of length 2<ul>
     *                      <li>args[0] should be an instance of {@link LispAtom}</li>
     *                      <li>args[1] transformer function</li></ul>
     * @return  result of transformer function stored in {@code args[1]} application to
     *          {@link LispAtom} instance stored in {@code args[0]}.
     */
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