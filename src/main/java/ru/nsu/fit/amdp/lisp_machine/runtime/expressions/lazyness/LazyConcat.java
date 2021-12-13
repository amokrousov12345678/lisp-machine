package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.List;
import java.util.stream.Collectors;

/**
 * lazy-cat implementation
 */
public class LazyConcat extends BuiltinOperation {

    /**
     * Lazily concatenate provided sequences
     *
     * @param args    list of 2 or more arguments. All arguments should support ISeq interface
     * @return lazy sequence of concatenation of provided sequences
     */
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
