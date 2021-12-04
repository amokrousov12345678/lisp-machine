package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;


import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class LispFn implements Expression{

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Too few args for fn");
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

        boolean isVarArg = false;
        if (argnamesList.size() > 0) {
            var lastArgName = argnamesList.get(argnamesList.size()-1);
            if (lastArgName.getName().charAt(0) == '&') {
                argnamesList.remove(lastArgName);
                argnamesList.add(new LispIdentifier(lastArgName.getName().substring(1)));
                isVarArg = true;
            }
        }
        return new LispFunction(context, args, argnamesList, isVarArg);

    }
}