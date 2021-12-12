package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

/**
 * Negation implementation
 */
public class LispNot extends LogicOperation {

    /**
     * @param args list of single boolean value wrapped in LispObject
     * @return negative from provided value wrapped in LispObject
     */
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
