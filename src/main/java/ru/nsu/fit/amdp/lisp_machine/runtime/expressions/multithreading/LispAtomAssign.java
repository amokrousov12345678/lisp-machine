package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * Assign atom implementation.
 */
public class LispAtomAssign extends LispBaseFunction {

    /**
     * Assign atom with value stored in {@code args[1]}
     *
     * @param args list of arguments of length 2<ul>
     *                      <li>args[0] should be an instance of {@link LispAtom}</li>
     *                      <li>args[1] new atom value</li></ul>
     * @return
     */
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