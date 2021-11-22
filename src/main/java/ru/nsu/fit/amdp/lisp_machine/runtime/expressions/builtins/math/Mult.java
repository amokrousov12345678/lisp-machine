package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

import java.util.List;

public class Mult extends ArithmeticOperation {

    @Override
    public Expression apply(Context context, List<Expression> args) {

        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Float)) {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::floatValue).
                    reduce(1.f, (a, b) -> a * b);
            return new LispObject(res);
        } else {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::intValue).
                    reduce(1, (a, b) -> a * b);
            return new LispObject(res);
        }
    }

    @Override
    public String toString() {
        return "*";
    }
}
