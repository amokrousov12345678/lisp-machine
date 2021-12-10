package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConcatResult implements Expression, ISeq {

    private List<ISeq> sequences;
    private Expression first = null;
    private ISeq next = null;

    public ConcatResult (List<ISeq> sequences) {
        this.sequences = sequences;
    }

    // TODO: make a common method for state modification as in LazySeqNode

    private void propagate() {
        List<ISeq> sequencesCopy = sequences;

        ISeq firstAliveSeq = sequencesCopy.remove(0);
        Expression firstAliveFirst = firstAliveSeq.first();
        ISeq firstAliveNext = firstAliveSeq.next();

        while (firstAliveFirst == null && !sequencesCopy.isEmpty()) {
            firstAliveSeq = sequencesCopy.remove(0);
            firstAliveFirst = firstAliveSeq.first();
            firstAliveNext = firstAliveSeq.next();
        }

        first = firstAliveFirst;

        if (firstAliveNext != null && !sequencesCopy.isEmpty()) {
            sequencesCopy.add(0, firstAliveNext);
            next = new ConcatResult(sequencesCopy);
        } else if (firstAliveNext != null){
            next = firstAliveNext;
        } else if (!sequencesCopy.isEmpty()) {
            next = new ConcatResult(sequencesCopy);
        } else {
            next = null;
        }

        sequences = null;
    }

    @Override
    public Expression first() {
        if (sequences == null)
            return first;

        propagate();

        return first;
    }

    @Override
    public ISeq next() {
        if (sequences == null)
            return next;

        propagate();

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
