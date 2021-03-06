package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>Concat result node is a result of {@link LazyConcat lazy-cat} application to its arguments.
 * It stores provided list of {@link ISeq sequences} and on {@link ISeq#first() first}/{@link ISeq#next() next}
 * call performs this operation on the first provided non-empty sequence. Result of such evaluation
 * is stored as first in current node, exercised sequence in list is replaced with
 * its next and then that list is stored in the next of current node.</p>
 *
 * <p>Lazy sequence is represented as a singly linked list. Each node knows only about
 * its successor. This allows garbage collection of unused and not stored nodes of
 * lazy sequence. In case with ConcatResult it additionally eliminates unused lists
 * of sequences.</p>
 */
public class ConcatResult extends LazySeqNodeBase {

    private List<ISeq> sequences;
    private Expression first = null;
    private ISeq next = null;

    /**
     * Create lazy {@link ISeq sequence} of concatenation of provided {@link ISeq sequences}
     *
     * @param sequences {@link ISeq sequences} to be lazily concatenated
     */
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

    /**
     * ConcatResult node is considered to be computed, if it stores
     * no sequences.
     *
     * @return true if no sequences are stored in current node, otherwise false
     */
    @Override
    public boolean isComputed() {
        return sequences == null;
    }

    /**
     * <p>During computation, checks sequences for non-null first.
     * If such sequence is found, its first is stored in current
     * node, sequence itself is replaces with its next and
     * propagated to newly created ConcatResult node.</p>
     *
     * <p>If there is only one sequence left, does not create a new
     * ConcatResult node but stores that last sequence in this.next
     * instead.</p>
     */
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
