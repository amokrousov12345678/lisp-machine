package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

/**
 * List size
 */
public class ListCount extends BuiltinOperation {

    /**
     * Compute list size
     *
     * @param args LispExecutableList
     * @return length of LispExecutableList as long wrapped in LispObject
     *
     * @throws IllegalArgumentException if args.size != 1
     */
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

