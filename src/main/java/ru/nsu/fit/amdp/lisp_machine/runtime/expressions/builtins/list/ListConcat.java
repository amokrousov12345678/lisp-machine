package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.Collection;
import java.util.stream.Collectors;

public class ListConcat extends BuiltinOperation {

    @Override
    public Expression execute() {
        var args = getArgs();

        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for concat");

        if(!(args.stream().allMatch(a -> a instanceof LispObject)
             && args.stream().map(a -> ((LispObject) a).self())
                .allMatch(a -> a instanceof LispPersistentList)))
            throw new IllegalArgumentException("Incorrect arguments type for concat. Expected lists");

        var result = args.stream().map(a -> ((LispPersistentList) ((LispObject) a).self()).asList())
                        .flatMap(Collection::stream).collect(Collectors.toList());

        return new LispObject(new LispPersistentList(result));
    }

    @Override
    public String toString() {
        return "concat";
    }
}
