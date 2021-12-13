package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

/**
 * <p>Lazy sequence node is a result of lazy-seq application to its arguments.
 * It stores provided expression and context and not evaluates them until they
 * are needed.</p>
 *
 * <p>Lazy sequence is represented as singly linked list. Each node knows only about
 * its successor. This allows garbage collection of unused and not stored nodes of
 * lazy sequence, which is useful for infinite sequences.</p>
 */
public class LazySeqNode extends LazySeqNodeBase {

    private Expression first = null;
    private ISeq next = null;

    private Expression body;
    private Context context;

    /**
     * Create new lazy sequence node.
     *
     * @param body expression to be evaluated on first/next call.
     *             Provided expression should return sequence on evaluation.
     * @param context execution context for body
     */
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

    /**
     * LazySeqNode is considered to be
     * computed if it's body is null.
     *
     * @return body == null
     */
    @Override
    public boolean isComputed() {
        return body == null;
    }

    /**
     * Evaluates body to obtain first and next.
     * Then assigns body and context to null
     * to allow garbage collector do its job.
     * Additionally, body==null works as isComputed.
     */
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
