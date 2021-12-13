package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.multithreading;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

/**
 * Common interface for objects which could be dereferenced.
 */
public interface Derefable {

    /**
     * Dereference object.
     *
     * @return result of deref application to object.
     */
    Expression deref();
}
