package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

/**
 * Add implementation
 */
public class Add extends ArithmeticOperation {

    /**
     * Compute sum of provided arguments.
     * If at least one of arguments is double
     * returns double, otherwise long.
     *
     * @param args list of evaluated expressions
     * @return sum of provided elements
     */
    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Double)) {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::doubleValue).
                    reduce(0.0, Double::sum);
            return new LispObject(res);
        } else {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::longValue).
                    reduce(0L, Long::sum);
            return new LispObject(res);
        }
    }

    @Override
    public String toString() {
        return "+";
    }
}
