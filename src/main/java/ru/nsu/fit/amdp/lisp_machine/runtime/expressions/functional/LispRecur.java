package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispRecurHolder;

import java.util.List;

public class LispRecur extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        return new LispRecurHolder(args);
    }

}
