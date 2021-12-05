package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class Add extends ArithmeticOperation {

    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Float)) {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::floatValue).
                    reduce(0.f, Float::sum);
            return new LispObject(res);
        } else {
            var res = args.stream().
                    map(a -> (Number) ((LispObject) a).self()).
                    map(Number::intValue).
                    reduce(0, Integer::sum);
            return new LispObject(res);
        }
    }

    @Override
    public String toString() {
        return "+";
    }
}
