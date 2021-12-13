package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

/**
 * Common base class for logic functions
 */
abstract public class LogicOperation extends BuiltinOperation {

    /**
     * Checks whether all provided arguments are booleans wrapped in {@link LispObject}.
     *
     * @param args list of evaluated expressions
     */
    public void assertBooleanTypes(List<Expression> args){
        if (!args.stream().allMatch(a -> ((a instanceof LispObject) && (((LispObject) a).self() instanceof Boolean)))) {
            throw new IllegalArgumentException(this.toString() + " called with non boolean arguments");
        }
    }
}
