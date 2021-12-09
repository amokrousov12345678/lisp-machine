package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ConcatResult;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.List;
import java.util.stream.Collectors;

public class LazyConcat extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() < 2)
            throw new IllegalArgumentException("Incorrect amount of args for concat");

        if(!(args.stream().allMatch(a -> a instanceof ISeq)))
            throw new IllegalArgumentException("Incorrect arguments type for concat. Expected sequences");

        var sequences = args.stream().map(a -> ((ISeq) a))
                .collect(Collectors.toList());

        return new ConcatResult(sequences);
    }
}
