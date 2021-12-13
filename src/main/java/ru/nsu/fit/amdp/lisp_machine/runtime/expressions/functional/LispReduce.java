package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispQuotedExpression;

import java.util.List;

/**
 * reduce implementation
 */
public class LispReduce implements Expression {

    /**
     * Reduce args[1] with function args[0] using args[2] as initial value.
     *
     * @param context execution context
     * @param args    list of arguments of length 3<ul>
     *                      <li>args[0] should be an Expression which evaluates to a function with arity 2</li>
     *                      <li>args[1] sequence to reduce</li>
     *                      <li>args[2] reduce initial value</li></ul>
     *
     * @return result of reduction of args[1] with function args[0] using args[2] as initial value.
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 3) {
            throw new IllegalArgumentException("Incorrect number arguments for reduce");
        }

        Expression function = args.get(0).evaluate(context);
        Expression arg = args.get(1).evaluate(context);
        Expression initialAcc = args.get(2).evaluate(context);

        if (!(arg instanceof ISeq)) {
           throw new RuntimeException("Attempt to apply reduce to non-seq");
        }

        Expression acc = initialAcc;
        ISeq seq = (ISeq) arg;
        Expression first = seq.first();

        while (first != null) {
            acc = function.apply(context, List.of(new LispQuotedExpression(acc), first));
            seq = seq.next();
            if (seq == null)
                break;
            first = seq.first();
        }

        return acc;
    }
}
