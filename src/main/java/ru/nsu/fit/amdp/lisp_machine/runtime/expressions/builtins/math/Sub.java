package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Subtraction implementation
 */
public class Sub extends ArithmeticOperation{

    /**
     * Compute difference of 2 provided arguments.
     * If more arguments are provided treats them
     * as additional subtrahends.
     *
     * If at least one of arguments is double
     * returns double, otherwise long.
     *
     * @param args list of evaluated expressions
     * @return difference of provided elements
     */
    @Override
    public Expression execute(List<Expression> args) {
        if (args.size() == 1) {
            args.add(0, new LispObject(0));

        }

        assertNumberTypes(args);

        var headValue = ((Number) ((LispObject) args.get(0)).self()).doubleValue();
        for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
            var val = ((Number) ((LispObject) arg).self()).doubleValue();
            headValue -= val;
        }
        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Double)) {
            return new LispObject(headValue);
        } else {
            return new LispObject((long) headValue);
        }

    }

    @Override
    public String toString() {
        return "-";
    }
}
