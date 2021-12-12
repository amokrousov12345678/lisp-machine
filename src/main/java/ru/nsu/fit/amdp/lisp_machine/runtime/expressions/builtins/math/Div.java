package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Division implementation
 */
public class Div extends ArithmeticOperation{

    /**
     * Compute quotient of 2 provided arguments.
     * If more arguments are provided treats them
     * as additional divisors.
     *
     * If at least one of arguments is double
     * returns double, otherwise long.
     *
     * @param args list of evaluated expressions
     * @return quotient of provided elements
     */
    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Double)) {
            var headValue = ((Number) ((LispObject) args.get(0)).self()).doubleValue();
            for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
                var val = ((Number) ((LispObject) arg).self()).doubleValue();
                headValue /= val;
            }
            return new LispObject(headValue);
        } else {
            var headValue = ((Number) ((LispObject) args.get(0)).self()).longValue();
            for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
                var val = ((Number) ((LispObject) arg).self()).longValue();
                headValue /= val;
            }
            return new LispObject(headValue);
        }
    }

    @Override
    public String toString() {
        return "/";
    }
}
