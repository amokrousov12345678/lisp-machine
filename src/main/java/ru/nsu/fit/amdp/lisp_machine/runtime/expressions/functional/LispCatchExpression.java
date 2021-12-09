package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils.LispQuote;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.List;
import java.util.stream.Collectors;

public class LispCatchExpression implements Expression {

    private final Class exceptionClass;
    private final Expression exceptionHandler;

    public LispCatchExpression(Class exceptionClass, Expression exceptionHandler) {
        this.exceptionClass = exceptionClass;
        this.exceptionHandler = exceptionHandler;
    }

    public boolean canCatch(Throwable throwable) {
       return (exceptionClass.isAssignableFrom(throwable.getClass()));
    }
}

