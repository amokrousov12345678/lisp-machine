package ru.nsu.fit.amdp.lisp_machine.runtime.context;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispFunction;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LispContext implements Context{

    private Map<String, Expression> context;

    public LispContext() {
        context = new HashMap<>();
    }
    public LispContext(Map<String, Expression> context) {
        this.context = context;
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
        context.put(name.getName(), value);
    }

    @Override
    public Context snapshot() {
        Map<String, Expression> snapshot = new HashMap<>(context);
        return new LispContext(snapshot);
    }

    @Override
    public boolean equals(Context other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispContext that = (LispContext) other;
        return Objects.equals(this.context, that.context);
    }
}
