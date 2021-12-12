package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;


import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispCatchExpression;

import java.util.List;
import java.util.stream.Collectors;

public class LispTry extends LispBaseFunction {

    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();
        if (args.size() < 2) {
            throw new IllegalArgumentException("Too few args for try");
        }

        var body = ((LispBaseFunction) args.remove(0));

        LispCatchExpression[] catches = args.stream().map(arg -> (LispCatchExpression) arg).toArray(LispCatchExpression[]::new);

        try {
            return body.apply(context, List.of());
        } catch (Throwable throwable) {
            for (int i=0;i<catches.length;i++) {
                if (catches[i].canCatch(throwable)) {
                    return catches[i].doCatch(throwable);
                }
            }
            throw throwable;
        }
    }
}
