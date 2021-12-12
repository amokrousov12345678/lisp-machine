package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

/**
 * Division remainder implementation
 */
public class Mod extends ArithmeticOperation {

    /**
     * Compute remainder of the division of
     * args[0] on args[1].
     *
     * @param args list of evaluated expressions
     * @return remainder of the division of args[0] on args[1].
     *
     * @throws IllegalArgumentException if args.size != 2
     */
    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if(args.size() != 2)
            throw new IllegalArgumentException("Incorrect amount of args for mod");

        var a = ((Number) ((LispObject) args.get(0)).self()).longValue();
        var b = ((Number) ((LispObject) args.get(1)).self()).longValue();
        return new LispObject(a % b);
    }

    @Override
    public String toString() {
        return "mod";
    }
}
