package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispBaseFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispMacroExpression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.List;

/**
 * Macro expression expander
 */
public class LispMacroExpandOnce extends LispBaseFunction {

    /**
     * Perform macros substitution in provided expression
     *
     * @param args list of expressions for macros substitution.
     *             The first expression in list should be a previously
     *             defined macro.
     * @return result of macro substitutions on provided expression
     */
    @Override
    public Expression execute(List<Expression> args) {
        var context = getContext();

        if (args.size() != 1) {
            throw new IllegalArgumentException("Invalid count of arguments for macroexpand");
        }
        List<Expression> argList = ((LispExecutableList) args.get(0)).asList();
        Expression firstArg = argList.get(0).evaluate(context);
        if (!(firstArg instanceof LispMacroExpression)) {
            return args.get(0);
        }
        argList.remove(0);
        return ((LispMacroExpression) firstArg).expand(context, argList);
    }
}