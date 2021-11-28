package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

public class LispOr extends LogicOperation{
    @Override
    public Expression execute() {
        var args = getArgs();

        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for OR");

        assertBooleanTypes(args);

        var result = args.stream().map(a -> (Boolean) ((LispObject) a).self())
                .reduce(Boolean::logicalOr).orElse(true);

        return new LispObject(result);
    }

    @Override
    public String toString() {
        return "or";
    }
}
