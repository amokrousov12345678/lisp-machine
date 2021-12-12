package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

/**
 * Eq implementation
 */
public class LispEquals extends BuiltinOperation {
    /**
     * Checks all provided args for equality
     *
     * @param args list of evaluated expressions
     * @return true if all provided args are equal, otherwise false
     */
    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for equals");

        return new LispObject(args.stream().allMatch(args.get(0)::equals));
    }

    @Override
    public String toString() {
        return "eq";
    }
}
