package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;

import java.util.List;

public class CreateList extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {
        return new LispExecutableList(args);
    }

    @Override
    public String toString() {
        return "list";
    }

}
