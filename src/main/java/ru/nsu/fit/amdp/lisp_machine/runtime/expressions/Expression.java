package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;

import java.util.List;

public interface Expression {

    Expression evaluate(Context context);

    default Expression apply(Context context, List<Expression> args) {
        throw new NotCallableObjectError(this.toString());
    }
}
