package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

public class LispEquals extends BuiltinOperation {
    @Override
    public Expression execute() {
        var args = getArgs();

        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for equals");

        return new LispObject(args.stream().allMatch(args.get(0)::equals));
    }

    @Override
    public String toString() {
        return "eq";
    }
}
