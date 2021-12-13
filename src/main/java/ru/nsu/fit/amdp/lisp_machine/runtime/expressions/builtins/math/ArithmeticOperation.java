package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

/**
 * Common base class for arithmetic functions
 */
public abstract class ArithmeticOperation extends BuiltinOperation {

    /**
     * Checks whether all provided arguments are numbers wrapped in {@link LispObject}.
     *
     * @param args list of evaluated expressions
     */
    public void assertNumberTypes(List<Expression> args){
        if (!args.stream().allMatch(a -> ((a instanceof LispObject) && (((LispObject) a).self() instanceof Number)))) {
            throw new IllegalArgumentException(this.toString() + " called with non numbers arguments");
        }
    }
}
