package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;
import java.util.stream.Collectors;

public class Div extends ArithmeticOperation{

    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Float)) {
            var headValue = ((Number) ((LispObject) args.get(0)).self()).floatValue();
            for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
                var val = ((Number) ((LispObject) arg).self()).floatValue();
                headValue /= val;
            }
            return new LispObject(headValue);
        } else {
            var headValue = ((Number) ((LispObject) args.get(0)).self()).intValue();
            for (Expression arg : args.stream().skip(1).collect(Collectors.toList())) {
                var val = ((Number) ((LispObject) arg).self()).intValue();
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
