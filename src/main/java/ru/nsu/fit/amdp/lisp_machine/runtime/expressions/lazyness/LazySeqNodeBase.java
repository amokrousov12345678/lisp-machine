package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Common base class for lazy sequence nodes
 */
public abstract class LazySeqNodeBase implements Expression, ISeq {

    /**
     * @return value stored in current node or null if there is no value
     */
    public abstract Expression getFirst();

    /**
     * @return value stored in the next node or null if there is no next node
     */
    public abstract ISeq getNext();

    /**
     * @return true if current node values were already computed, otherwise false
     */
    public abstract boolean isComputed();

    /**
     * Implement this function to compute first and next for given sequence
     * and raise computation flag.
     */
    public abstract void compute();


    /**
     * @return the first element of the sequence (or null if empty),
     *         if it is already evaluated, or evaluates it and then returns
     */
    @Override
    public Expression first() {
        if (this.isComputed())
            return getFirst();

        compute();

        return getFirst();
    }

    /**
     * @return sequence without the first element (or null if empty),
     *         if it is already evaluated, or evaluates it and then returns
     */
    @Override
    public ISeq next() {
        if (this.isComputed())
            return getNext();

        compute();

        return getNext();
    }

    /**
     * Compute all nodes of the sequence and return
     * its string representation.
     *
     * Be careful with infinite sequences!
     *
     * @return string representation of sequence
     */
    @Override
    public String toString() {
        List<Expression> expressions = new ArrayList<>();

        ISeq seq = this;
        Expression expr = this.first();

        while (expr != null) {
            expressions.add(expr);
            seq = seq.next();
            expr = seq == null ? null : seq.first();
        }

        return expressions.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" ", "(", ")"));
    }
}
