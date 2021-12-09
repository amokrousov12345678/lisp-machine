package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

public class LispRecurHolder implements Expression {
    private final List<Expression> inmate;

    public LispRecurHolder(List<Expression> inmate) {
        this.inmate = inmate;
    }

    @Override
    public Expression evaluate(Context context) {
        throw new NotCallableObjectError(this.toString());
    }

    public List<Expression> getInmate() {
        return inmate;
    }
}
