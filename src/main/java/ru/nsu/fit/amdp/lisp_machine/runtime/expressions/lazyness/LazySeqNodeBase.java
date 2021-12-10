package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class LazySeqNodeBase implements Expression, ISeq {

    public abstract Expression getFirst();
    public abstract ISeq getNext();
    public abstract boolean isComputed();
    public abstract void compute();

    @Override
    public Expression first() {
        if (this.isComputed())
            return getFirst();

        compute();

        return getFirst();
    }

    @Override
    public ISeq next() {
        if (this.isComputed())
            return getNext();

        compute();

        return getNext();
    }

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
