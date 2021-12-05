package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ListRest extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for last");

        var list = ((LispExecutableList) args.get(0));

        if (list.size() == 0)
            return new LispExecutableList(new LinkedList<>());

        var listCopy = list.asList().stream().skip(1).collect(Collectors.toList());
        return new LispExecutableList(listCopy);
    }

    @Override
    public String toString() {
        return "last";
    }

}
