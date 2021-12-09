package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;

import java.util.List;

public class LispRecur extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        return new LispRecurHolder(args);
    }

}
