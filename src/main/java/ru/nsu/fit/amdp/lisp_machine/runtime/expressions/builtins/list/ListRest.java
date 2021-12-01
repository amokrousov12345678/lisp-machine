package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRest extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for last");

        var list = (LispPersistentList)((LispObject) args.get(0)).self();

        if (list.size() == 0)
            return new LispObject(new LispPersistentList(new LinkedList<Expression>()));

        var listCopy = list.asList().stream().skip(1).collect(Collectors.toList());
        return new LispObject(new LispPersistentList(listCopy));
    }

    @Override
    public String toString() {
        return "last";
    }

}
