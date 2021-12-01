package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class LispBaseFunction implements Expression {

    private Context context;

    @Override
    public Expression apply(Context context, List<Expression> args) {
        this.context = context;
        var argsValues = args.stream()
                .map(arg -> arg.evaluate(this.context))
                .collect(Collectors.toList());

        return execute(argsValues);
    }

    @Override
    public Expression evaluate(Context context){
        return this;
    }

    public abstract Expression execute(List<Expression> args);

    public Context getContext() { return context; }

    @Override
    public boolean equals(Expression other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispBaseFunction that = (LispBaseFunction) other;
        return Objects.equals(this.context, that.context);
    }
}
