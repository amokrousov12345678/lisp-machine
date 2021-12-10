package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConcatResult extends LazySeqNodeBase {

    private List<ISeq> sequences;
    private Expression first = null;
    private ISeq next = null;

    public ConcatResult (List<ISeq> sequences) {
        this.sequences = sequences;
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
        return sequences == null;
    }

    @Override
    public void compute() {
        ISeq firstAliveSeq = sequences.remove(0);
        Expression firstAliveFirst = firstAliveSeq.first();
        ISeq firstAliveNext = firstAliveSeq.next();

        while (firstAliveFirst == null && !sequences.isEmpty()) {
            firstAliveSeq = sequences.remove(0);
            firstAliveFirst = firstAliveSeq.first();
            firstAliveNext = firstAliveSeq.next();
        }

        first = firstAliveFirst;

        if (firstAliveNext != null && !sequences.isEmpty()) {
            sequences.add(0, firstAliveNext);
            next = new ConcatResult(sequences);
        } else if (firstAliveNext != null){
            next = firstAliveNext;
        } else if (!sequences.isEmpty()) {
            next = new ConcatResult(sequences);
        } else {
            next = null;
        }

        sequences = null;
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
