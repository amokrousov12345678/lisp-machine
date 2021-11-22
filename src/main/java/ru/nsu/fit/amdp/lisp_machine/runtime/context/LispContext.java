package ru.nsu.fit.amdp.lisp_machine.runtime.context;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LispContext implements Context{

    private Map<String, Expression> context;

    public LispContext() {
        context = new HashMap<>();
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
}
