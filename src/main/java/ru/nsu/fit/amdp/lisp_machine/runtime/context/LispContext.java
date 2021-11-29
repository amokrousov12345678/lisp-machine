package ru.nsu.fit.amdp.lisp_machine.runtime.context;

import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LispContext implements Context {

    private PMap<String, Expression> context;

    public LispContext() {
        context = HashTreePMap.empty();
    }

    @Override
    public Optional<Expression> getByName(LispIdentifier name) {
        Expression expression = context.get(name.getName());

        if (expression != null) {
            return Optional.of(expression);
        }

        return Optional.empty();
    }

    @Override
    public void define(LispIdentifier name, Expression value) {
        context = context.plus(name.getName(), value);
    }

    @Override
    public Context makeCopy() {
        LispContext result = new LispContext();
        result.context = context;
        return result;
    }

    @Override
    public boolean equals(Context other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispContext that = (LispContext) other;
        return Objects.equals(this.context, that.context);
    }
}
