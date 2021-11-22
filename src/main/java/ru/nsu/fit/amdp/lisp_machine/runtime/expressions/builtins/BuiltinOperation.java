package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispBaseFunction;

import java.util.List;

public abstract class BuiltinOperation extends LispBaseFunction {

    // On evaluate return operation itself
    @Override
    public Expression evaluate(Context context){
        return this;
    }
}
