package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispCatchExpression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispMacroExpression;

import java.util.List;

public class LispCatch implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Too few args for catch block");
        }

        String exceptionTypeName = ((LispIdentifier) (args.remove(0))).getName();
        Expression exceptionHandler = args.remove(0).evaluate(context);
        try {
            return new LispCatchExpression(Class.forName(exceptionTypeName), exceptionHandler);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Exception class isn't defined");
        }
    }
}