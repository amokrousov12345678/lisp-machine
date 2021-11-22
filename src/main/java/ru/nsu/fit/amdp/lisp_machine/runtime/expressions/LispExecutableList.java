package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.List;

public class LispExecutableList implements Expression{

    private final List<Expression> expressions;

    public LispExecutableList(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Expression evaluate(Context context) {
        var function = expressions.remove(0).evaluate(context);
        return function.apply(context, expressions);
    }


    /* For unit testing purposes (fix next time) */

    public int size() {
        return expressions.size();
    }

    public Expression get(int index) {
        return expressions.get(index);
    }

    public List<Expression> asList() {
        return expressions;
    }
}
