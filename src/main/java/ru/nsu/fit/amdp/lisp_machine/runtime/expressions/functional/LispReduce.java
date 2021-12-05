package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispQuotedExpression;

import java.util.List;

public class LispReduce implements Expression {
    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 3) {
            throw new IllegalArgumentException("Incorrect number arguments for reduce");
        }

        Expression function = args.get(0).evaluate(context);
        Expression arg = args.get(1).evaluate(context);
        Expression initialAcc = args.get(2).evaluate(context);

        if (!(arg instanceof LispExecutableList)) {
           throw new RuntimeException("Attempt to apply reduce to non-list");
        }

        return ((LispExecutableList) arg).asList().stream()
                .reduce(initialAcc,
                        // Additional quote added in order to prevent argument evaluation in classes
                        // derived from LispBaseFunction
                        (acc, e) -> function.apply(context, List.of(new LispQuotedExpression(acc), e)));
    }
}
