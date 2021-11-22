package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;

public abstract class BuiltinOperation implements Expression {

    // On evaluate return operation itself
    @Override
    public Expression evaluate(Context context){
        return this;
    }

    public abstract Expression apply(Context context, List<Expression> args);
}
