package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.utils.LispQuote;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;

import java.util.List;
import java.util.stream.Collectors;

public class LispMacroExpression implements Expression {

    private final Expression transformFunction;

    public LispMacroExpression(Expression transformFunction) {
        this.transformFunction = transformFunction;
    }

    public Expression getTransformFunction() {
        return transformFunction;
    }

    public Expression expand(Context context, List<Expression> args) {
        List<Expression> quotedArgs = args.stream().map(arg -> new LispExecutableList(List.of(new LispQuote(), arg))).collect(Collectors.toList());
        return transformFunction.apply(context, quotedArgs);
    }
    public Expression apply(Context context, List<Expression> args) {
        var expansionResult = expand(context, args);
        return expansionResult.evaluate(context);
    }

}

