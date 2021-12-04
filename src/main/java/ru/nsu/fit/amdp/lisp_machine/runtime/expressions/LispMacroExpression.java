package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import jdk.jshell.EvalException;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LispMacroExpression implements Expression {

    private final Expression transformFunction;

    public LispMacroExpression(Expression transformFunction) {
        this.transformFunction = transformFunction;
    }

    public Expression getTransformFunction() {
        return transformFunction;
    }

    public Expression expand(Context context, List<Expression> args) {
        List<Expression> quotedArgs = args.stream().map(arg -> new LispQuote().apply(context, List.of(arg))).collect(Collectors.toList());
        return transformFunction.apply(context, quotedArgs);
    }
    public Expression apply(Context context, List<Expression> args) {
        var expansionResult = expand(context, args);
        return new LispEval().apply(context, List.of(expansionResult)).evaluate(context);
    }

}

