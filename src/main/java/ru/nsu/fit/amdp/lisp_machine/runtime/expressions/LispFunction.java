package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LispFunction extends LispBaseFunction {

    private LispIdentifier name;
    private final Context closure;
    private final List<Expression> body;
    private final List<LispIdentifier> argnames;
    private final boolean isVararg;

    public LispFunction(Context context,
                        List<Expression> body,
                        List<LispIdentifier> argnames) {
        this.closure = context.snapshot();
        this.body = body;
        this.argnames = argnames;
        this.isVararg = false;
    }

    public LispFunction(Context context,
                        List<Expression> body,
                        List<LispIdentifier> argnames,
                        boolean isVararg) {
        this.closure = context.snapshot();
        this.body = body;
        this.argnames = argnames;
        this.isVararg = isVararg;
    }

    public void setName(LispIdentifier name) {
        this.name = name;
        closure.define(name, this);
    }

    @Override
    public Expression execute() {
        var args = this.getArgs();

        if (!isVararg && (args.size() != argnames.size()))
            throw new RuntimeException("Wrong amount of variables!!!");

        IntStream.range(0, isVararg ? (argnames.size() - 1) : argnames.size())
                .forEach(i -> closure.define(argnames.get(i),args.remove(i)));

        if (isVararg) {
            var lastArgname = argnames.get(argnames.size() - 1);
            closure.define(lastArgname,  new LispObject(new LispPersistentList(args)));
        }

        // TODO: Maybe it will be wrong without copying body
        var result = new LinkedList<>(body).stream()
                .map(expr -> expr.evaluate(closure))
                .reduce(null, (a,b) -> b);

        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispFunction that = (LispFunction) other;
        return Objects.equals(this.name, that.name)
                && Objects.equals(this.argnames, that.argnames)
                && Objects.equals(this.body, that.body)
                && Objects.equals(this.closure, that.closure)
                && (this.isVararg == that.isVararg);
    }
}
