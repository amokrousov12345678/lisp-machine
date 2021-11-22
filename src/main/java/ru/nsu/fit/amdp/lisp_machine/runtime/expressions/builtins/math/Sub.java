package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sub extends ArithmeticOperation{
    @Override
    public Expression execute() {

        var args = getArgs();

        if (args.size() == 1) {
            args.add(0, new LispObject(0));

        }

        assertNumberTypes(args);

        var headValue = ((Number) ((LispObject) args.get(0)).self()).floatValue();
        for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
            var val = ((Number) ((LispObject) arg).self()).floatValue();
            headValue -= val;
        }
        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Float)) {
            return new LispObject(headValue);
        } else {
            return new LispObject((int) headValue);
        }

    }

    @Override
    public String toString() {
        return "-";
    }
}
