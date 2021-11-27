package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.LinkedList;

public class ListFirst extends BuiltinOperation {

    @Override
    public Expression execute() {
        var args = getArgs();

        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for first");

        var list = (LispPersistentList)((LispObject) args.get(0)).self();

        if (list.size() == 0)
            return new LispObject(new LispPersistentList(new LinkedList<Expression>()));

        return list.get(0);
    }

    @Override
    public String toString() {
        return "first";
    }

}
