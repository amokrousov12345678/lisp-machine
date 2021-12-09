package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lazyness;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.LinkedList;
import java.util.List;

public class Doall extends LispBaseFunction {
    @Override
    public Expression execute(List<Expression> args) {

        if (args.size() != 1) {
            throw new IllegalArgumentException("Too mane args for doall");
        }

        ISeq seq = (ISeq) args.get(0);

        List<Expression> expressions = new LinkedList<>();

        Expression expr = seq.first();
        while (expr != null) {
            expressions.add(expr);
            seq = seq.next();
            expr = seq == null ? null : seq.first();
        }

        return new LispExecutableList(expressions);
    }
}
