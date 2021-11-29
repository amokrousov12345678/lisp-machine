package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class LispMacro implements Expression{

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Too few args for macro");
        }

        var argnames = args.remove(0);
        if (!(argnames instanceof LispExecutableList)) {
            throw new IllegalArgumentException("No argument list provided");
        }

        if (!((LispExecutableList) argnames).asList().stream()
                .allMatch(a -> a instanceof LispIdentifier))
            throw new IllegalArgumentException("One of provided arguments names is invalid");

        var argnamesList = ((LispExecutableList) argnames).asList()
                .stream().map(a -> (LispIdentifier) a).collect(Collectors.toList());

        return new LispMacroExpression(new LispFunction(context, args, argnamesList));
    }
}