package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.LinkedList;
import java.util.List;

public class ListFirst extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for first");

        if(!(args.get(0) instanceof ISeq))
            throw new IllegalArgumentException("Provided arg is not a sequence");

        var first = ((ISeq) args.get(0)).first();

        if (first == null)
            return LispObject.nil;

        return first;
    }

    @Override
    public String toString() {
        return "first";
    }

}
