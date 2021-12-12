package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

/**
 * Common interface for sequences of elements (collections)
 */
public interface ISeq extends Expression{
    /**
     * @return the first element of ISeq, or null if ISeq is empty
     */
    Expression first();

    /**
     * @return ISeq without the first element, ot null if there are no more elements
     */
    ISeq next();
}
