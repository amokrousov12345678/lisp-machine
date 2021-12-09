package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConcatResult implements Expression, ISeq {

    private final List<ISeq> sequences;
    private Expression first = null;
    private ConcatResult next = null;

    public ConcatResult (List<ISeq> sequences) {
        this.sequences = sequences;
    }

    // TODO: make a common method for state modification as in LazySeqNode

    @Override
    public Expression first() {
        if (first != null) {
            return first;
        }

        for (var seq : sequences) {
            first = seq.first();
            if (first != null)
                break;
        }

        return first;
    }

    @Override
    public ISeq next() {
        if (next != null)
            return next;

        List<ISeq> sequencesCopy = new LinkedList<>(sequences);
        ISeq firstAliveSeq = sequencesCopy.remove(0).next();

        if (firstAliveSeq == null) {
            if (sequencesCopy.isEmpty())
                return null;
            if (sequences.size() == 1)
                return sequences.get(0);

            next = new ConcatResult(sequencesCopy);
            return next;
        }

        sequencesCopy.add(0, firstAliveSeq);
        next = new ConcatResult(sequencesCopy);
        return next;
    }

    @Override
    public String toString(){
        List<Expression> expressions = new LinkedList<>();
        for (var seq : sequences) {
            Expression expr = seq.first();
            while (expr != null) {
                expressions.add(expr);
                seq = seq.next();
                expr = seq == null ? null : seq.first();
            }
        }
        return expressions.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" ", "(", ")"));
    }

}
