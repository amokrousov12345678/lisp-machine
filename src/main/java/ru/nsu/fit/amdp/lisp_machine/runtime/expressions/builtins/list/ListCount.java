package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

public class ListCount extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for count");

        var list = ((LispExecutableList) args.get(0));

        return new LispObject(list.size());
    }

    @Override
    public String toString() {
        return "count";
    }

}

