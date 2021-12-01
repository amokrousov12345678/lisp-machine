package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;

import java.util.List;

public class LispAnd extends LogicOperation{
    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for AND");

        assertBooleanTypes(args);

        var result = args.stream().map(a -> (Boolean) ((LispObject) a).self())
                .reduce(Boolean::logicalAnd).orElse(false);

        return new LispObject(result);
    }

    @Override
    public String toString() {
        return "and";
    }
}
