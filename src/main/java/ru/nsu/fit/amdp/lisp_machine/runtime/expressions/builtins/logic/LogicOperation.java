package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

abstract public class LogicOperation extends BuiltinOperation {
    public void assertBooleanTypes(List<Expression> args){
        if (!args.stream().allMatch(a -> ((a instanceof LispObject) && (((LispObject) a).self() instanceof Boolean)))) {
            throw new IllegalArgumentException(this.toString() + " called with non boolean arguments");
        }
    }
}
