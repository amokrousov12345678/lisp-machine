package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.ArithmeticOperation;

import java.util.List;

public class LispLess extends ArithmeticOperation {
    @Override
    public Expression execute(List<Expression> args) {
        assertNumberTypes(args);

        if(args.size() != 2)
            throw new IllegalArgumentException("Incorrect amount of args for less");

        if (args.stream().anyMatch(a -> ((LispObject) a).self() instanceof Double)) {
            var res = ((Number) ((LispObject) args.get(0)).self()).doubleValue()
                    < ((Number) ((LispObject) args.get(1)).self()).doubleValue();
            return new LispObject(res);
        } else {
            var res = ((Number) ((LispObject) args.get(0)).self()).longValue()
                    < ((Number) ((LispObject) args.get(1)).self()).longValue();
            return new LispObject(res);
        }
    }

    @Override
    public String toString() {
        return "<";
    }
}
