package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

public abstract class ArithmeticOperation extends BuiltinOperation {
    public void assertNumberTypes(List<Expression> args){
        if (!args.stream().allMatch(a -> ((a instanceof LispObject) && (((LispObject) a).self() instanceof Number)))) {
            throw new IllegalArgumentException(this.toString() + " called with non numbers arguments");
        }
    }
}
