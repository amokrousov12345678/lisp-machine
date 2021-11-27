package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public class LispDefun implements Expression{

    @Override
    public Expression apply(Context context, List<Expression> args) {

        if (args.size() < 3) {
            throw new IllegalArgumentException("Too few args for def");
        }

        if (!(args.get(0) instanceof LispIdentifier)) {
            throw new IllegalArgumentException("No valid identifier provided");
        }

        var id = (LispIdentifier) args.remove(0);

        var argnames = args.remove(0);
        if (!(argnames instanceof LispExecutableList)) {
            throw new IllegalArgumentException("No argument list provided");
        }

        if (!((LispExecutableList) argnames).asList().stream()
                .allMatch(a -> a instanceof LispIdentifier))
            throw new IllegalArgumentException("One of provided arguments names is invalid");

        var argnamesList = ((LispExecutableList) argnames).asList()
                .stream().map(a -> (LispIdentifier) a).collect(Collectors.toList());

        context.define(id, new LispFunction(context, args, argnamesList));

        return this;
    }

}
