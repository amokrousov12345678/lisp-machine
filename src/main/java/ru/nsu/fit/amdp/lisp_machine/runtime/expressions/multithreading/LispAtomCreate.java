package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

/**
 * Atom creation
 */
public class LispAtomCreate extends LispBaseFunction {

    /**
     * Creates new {@link LispAtom} instance and initializes it with
     * value stored in {@code args[0]}.
     *
     * @param args evaluated value to be stored in atom.
     * @return {@link LispAtom} instance initialized with value stored in {@code args[0]}.
     */
    @Override
    public Expression execute(List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too few args");
        }
        return new LispAtom(args.get(0));
    }
}