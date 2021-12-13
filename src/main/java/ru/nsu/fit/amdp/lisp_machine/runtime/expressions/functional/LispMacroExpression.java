package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils.LispQuote;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to represent user-defined macros.
 */
public class LispMacroExpression implements Expression {

    private final Expression transformFunction;

    /**
     * Create new macro expression
     *
     * @param transformFunction function to be called on macros' arguments.
     */
    public LispMacroExpression(Expression transformFunction) {
        this.transformFunction = transformFunction;
    }

    /**
     * Get transformer function.
     *
     * @return transformer function.
     */
    public Expression getTransformFunction() {
        return transformFunction;
    }

    /**
     * Apply transform function to provided args
     * without args evaluation.
     *
     * @param context context.
     * @param args list of expression for macro expansion.
     * @return macro expansion of provided args.
     */
    public Expression expand(Context context, List<Expression> args) {
        List<Expression> quotedArgs = args.stream().map(arg -> new LispExecutableList(List.of(new LispQuote(), arg))).collect(Collectors.toList());
        return transformFunction.apply(context, quotedArgs);
    }

    /**
     * Apply macro to provided expressions and
     * evaluate result
     *
     * @param context context.
     * @param args    list of arguments.
     * @return evaluated result of macros substitution.
     */
    public Expression apply(Context context, List<Expression> args) {
        var expansionResult = expand(context, args);
        return expansionResult.evaluate(context);
    }

}

