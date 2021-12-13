package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.List;

/**
 * Lazy sequences creator
 */
public class LazySeq implements Expression {

    /**
     * @param context execution context
     * @param args    list of arguments of len 1. args[0] should be an expression
     *                which is going to be evaluated on first or rest call on returned {@link ISeq sequence}
     * @return {@link LazySeqNode head of a lazy sequence}
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too many args for lazy-seq");
        }

        return new LazySeqNode(args.get(0), context);
    }
}
