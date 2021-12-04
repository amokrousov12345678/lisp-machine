package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListConcat extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for concat");

        if(!(args.stream().allMatch(a -> a instanceof LispExecutableList)))
            throw new IllegalArgumentException("Incorrect arguments type for concat. Expected lists");

        var result = args.stream().map(a -> ((LispExecutableList) a).asList())
                        .flatMap(Collection::stream).collect(Collectors.toList());

        return new LispExecutableList(result);
    }

    @Override
    public String toString() {
        return "concat";
    }
}
