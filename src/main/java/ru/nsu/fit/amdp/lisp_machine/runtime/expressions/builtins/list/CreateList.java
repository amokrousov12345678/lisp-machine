package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispObject;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.List;

public class CreateList extends BuiltinOperation {

    @Override
    public Expression execute(List<Expression> args) {

        return new LispObject(new LispPersistentList(args));

    }

    @Override
    public String toString() {
        return "list";
    }

}
