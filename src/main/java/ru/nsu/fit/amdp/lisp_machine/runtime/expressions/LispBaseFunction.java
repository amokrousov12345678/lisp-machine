package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.stream.Collectors;

public abstract class LispBaseFunction implements Expression {

    private Context context;
    private List<Expression> args;

    @Override
    public Expression apply(Context context, List<Expression> args) {
        this.context = context;
        this.args = args.stream()
                .map(arg -> arg.evaluate(this.context))
                .collect(Collectors.toList());

        return execute();
    }

    public abstract Expression execute();

    public Context getContext() { return context; }
    public List<Expression> getArgs() { return args; }
}
