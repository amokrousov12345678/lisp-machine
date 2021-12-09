package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class Mult extends ArithmeticOperation {

    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Double)) {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::doubleValue).
                    reduce(1.0, (a, b) -> a * b);
            return new LispObject(res);
        } else {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::longValue).
                    reduce(1L, (a, b) -> a * b);
            return new LispObject(res);
        }
    }

    @Override
    public String toString() {
        return "*";
    }
}
