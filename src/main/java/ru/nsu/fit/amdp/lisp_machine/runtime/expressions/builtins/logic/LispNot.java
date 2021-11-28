package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

public class LispNot extends BuiltinOperation {
    @Override
    public Expression execute() {
        var args = getArgs();

        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for NOT");

        var obj = args.get(0);

        if (!(obj instanceof LispObject && ((LispObject) obj).self() instanceof Boolean))
            throw new IllegalArgumentException("Incorrect argument type for NOT. Should be boolean");

        return new LispObject(!(Boolean) ((LispObject) obj).self());
    }

    @Override
    public String toString() {
        return "!";
    }
}
