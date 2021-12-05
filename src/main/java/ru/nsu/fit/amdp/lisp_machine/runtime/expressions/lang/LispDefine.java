package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispMacroExpression;

import java.util.List;

public class LispDefine implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() != 2) {
            throw new IllegalArgumentException("Invalid arguments count for def");
        }

        if (!(args.get(0) instanceof LispIdentifier)) {
            throw new IllegalArgumentException("No valid identifier provided");
        }

        var id = (LispIdentifier) args.get(0);

        var value = args.get(1).evaluate(context);
        if (value instanceof LispFunction) {
            ((LispFunction) value).getClosure().define(id, value);
        }
        if (value instanceof LispMacroExpression) {
            var transformFunction = ((LispMacroExpression) value).getTransformFunction();
            if (transformFunction instanceof LispFunction) {
                ((LispFunction) transformFunction).getClosure().define(id, value);
            }
        }
        context.define(id, value);

        return this;
    }

}
