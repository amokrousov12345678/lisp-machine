package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LispExecutableList implements Expression {

    private final List<Expression> expressions;

    public LispExecutableList(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public Expression evaluate(Context context) {
        var copy = new LinkedList<>(expressions);
        var function = copy.remove(0).evaluate(context);
        return function.apply(context, copy);
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        LispExecutableList that = (LispExecutableList) other;
        return Objects.equals(this.expressions, that.expressions);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Expression)
            return this.equals((Expression) o);
        return false;
    }
    /* For unit testing purposes (fix next time) */

    @Override
    public String toString(){
        return expressions.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" ", "(", ")"));
    }

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
