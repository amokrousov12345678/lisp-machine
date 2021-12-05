package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class LispNot extends LogicOperation {
    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for NOT");

        assertBooleanTypes(args);

        return new LispObject(!(Boolean) ((LispObject) args.get(0)).self());
    }

    @Override
    public String toString() {
        return "!";
    }
}
