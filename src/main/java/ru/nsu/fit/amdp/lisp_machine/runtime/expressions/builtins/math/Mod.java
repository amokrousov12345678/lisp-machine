package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class Mod extends ArithmeticOperation {
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
