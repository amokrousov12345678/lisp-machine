package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

public class LazySeqNode extends LazySeqNodeBase {

    private Expression first = null;
    private ISeq next = null;

    private Expression body;
    private Context context;

    public LazySeqNode (Expression body, Context context) {
        this.body = body;
        this.context = context;
    }

    @Override
    public Expression getFirst() {
        return first;
    }

    @Override
    public ISeq getNext() {
        return next;
    }

    @Override
    public boolean isComputed() {
        return body == null;
    }

    @Override
    public void compute() {
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
}
