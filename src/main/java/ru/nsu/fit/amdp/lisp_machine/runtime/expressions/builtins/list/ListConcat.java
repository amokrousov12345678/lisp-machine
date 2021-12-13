package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List concat implementation
 */
public class ListConcat extends BuiltinOperation {

    /**
     * (concat (list 1 2 3) (list 3 4)) -{@literal >} (1 2 3 3 4)
     *
     * @param args list of LispExecutableLists
     * @return concatenation of provided lists as new LispExecutableList
     */
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
