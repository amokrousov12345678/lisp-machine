package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;

public class LispIf implements Expression {

    /**
     * @param context execution context
     * @param args    list of arguments of length 3<ul>
     *                      <li>args[0] should be an Expression which evaluates to LispObject of true/false</li>
     *                      <li>args[1] Expression to be evaluated and returned if args[0] is true</li>
     *                      <li>args[2] Expression to be evaluated and returned if args[0] is false</li></ul>
     *
     * @return args[1].evaluate(context) if args[0].evaluate.evaluate(context) == true, otherwise args[2].evaluate().evaluate(context)
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 3) {
            throw new IllegalArgumentException("Incorrect number arguments for if");
        }

        var cond = args.get(0).evaluate(context);

        if (!(cond instanceof LispObject) || !(((LispObject) cond).self() instanceof Boolean)) {
            throw new IllegalArgumentException("Predicate for if MUST be boolean");
        }

        boolean condValue = (Boolean) ((LispObject) cond).self();

        if (condValue) {
            return args.get(1).evaluate(context);
        } else {
            return args.get(2).evaluate(context);
        }
    }

}
