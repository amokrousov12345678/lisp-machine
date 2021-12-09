package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

public class LazySeqNode implements Expression, ISeq {

    private Expression first = null;
    private ISeq next = null;

    private Expression body;
    private Context context;

    public LazySeqNode (Expression body, Context context) {
        this.body = body;
        this.context = context;
    }

    private void execute() {
        var result = body.evaluate(context);
        if (!(result instanceof ISeq)) {
            first = result;
        } else {
            first = ((ISeq) result).first();
            next = ((ISeq) result).next();
        }
        body = null;
        context = null;
    }

    @Override
    public Expression first() {
        if (body == null)
            return first;

        execute();

        return first;
    }

    @Override
    public ISeq next() {
        if (body == null)
            return next;

        execute();

        return next;
    }
}
