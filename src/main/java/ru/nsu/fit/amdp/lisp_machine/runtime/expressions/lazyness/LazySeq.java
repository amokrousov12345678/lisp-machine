package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;
public class LazySeq implements Expression {
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Too many args for lazy-seq");
        }

        return new LazySeqNode(args.get(0), context);
    }
}
