package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.LispException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispQuotedExpression;

import java.util.List;

/**
 * Catch block implementation.
 * Created by {@link ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispCatch LispCatch}.
 */
public class LispCatchExpression implements Expression {

    private final Class<?> exceptionClass;
    private final Expression exceptionHandler;

    /**
     * Define handler function for provided exceptionClass
     *
     * @param exceptionClass class of exception
     * @param exceptionHandler expression which is going to be applied
     *                         to the thrown exception of provided class
     */
    public LispCatchExpression(Class exceptionClass, Expression exceptionHandler) {
        this.exceptionClass = exceptionClass;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Check whether this block can handle
     * thrown exception.
     *
     * @param throwable exception
     * @return true if exception could be handled by handler function, otherwise false
     */
    public boolean canCatch(Throwable throwable) {
       throwable = LispException.unwrap(throwable);
       return (exceptionClass.isAssignableFrom(throwable.getClass()));
    }

    /**
     * Handle thrown exception.
     *
     * @param throwable exception
     * @return result of handler application to provided exception
     */
    public Expression doCatch(Throwable throwable) {
        throwable = LispException.unwrap(throwable);
        return exceptionHandler.apply(null, List.of(new LispQuotedExpression(new LispObject(throwable))));
    }
}

