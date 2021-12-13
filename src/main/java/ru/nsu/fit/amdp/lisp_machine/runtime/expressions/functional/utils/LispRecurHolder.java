package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

/**
 * Object created by {@link LispRecur} to hold expressions until they are
 * used for function recursive call.
 */
public class LispRecurHolder implements Expression {
    private final List<Expression> inmate;

    /**
     * @param inmate list of expressions to be held
     */
    public LispRecurHolder(List<Expression> inmate) {
        this.inmate = inmate;
    }

    /**
     * @param context execution context
     * @return does not return
     * @throws NotCallableObjectError if called
     */
    @Override
    public Expression evaluate(Context context) {
        throw new NotCallableObjectError(this.toString());
    }

    /**
     * @return list of expressions stored in the current object
     */
    public List<Expression> getInmate() {
        return inmate;
    }
}
