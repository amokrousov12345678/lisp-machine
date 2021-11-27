package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.LinkedList;
import java.util.List;

public class LispExecutableList implements Expression{

    private final List<Expression> expressions;

    public LispExecutableList(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public LispExecutableList (LispPersistentList list) {
        expressions = list.asList();
    }

    @Override
    public Expression evaluate(Context context) {
        var copy = new LinkedList<>(expressions);
        var function = copy.remove(0).evaluate(context);
        return function.apply(context, copy);
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
