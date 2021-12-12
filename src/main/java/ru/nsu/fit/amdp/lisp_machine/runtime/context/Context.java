package ru.nsu.fit.amdp.lisp_machine.runtime.context;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Optional;

/**
 * Context. It is a place where all variables are stored.
 * Every variable has a name of LispIdentifier and value of Expression.
 */
public interface Context {

    /**
     * @param name LispIdentifier to look up in context
     * @return corresponding Expression or nullopt if there is no expression with provided name in context
     */
    Optional<Expression> getByName(LispIdentifier name);

    /**
     * Adds record to context. Defines variable 'name' with value 'value'
     * @param name
     * @param value
     */
    void define(LispIdentifier name, Expression value);

    /**
     * @return copy of current context
     */
    Context makeCopy();

    boolean equals(Context other);
}
