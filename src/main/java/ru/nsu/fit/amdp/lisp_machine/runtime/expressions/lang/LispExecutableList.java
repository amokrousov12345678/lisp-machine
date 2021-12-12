package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * List. Used both as sequence if elements and basic syntax "brick".
 * In order to create list as collection of elements you should write (list e1 e2 ... en).
 * Every list in written program is expected to have function ot the first place and
 * all rest elements are treated as that function's arguments.
 */
public class LispExecutableList implements Expression, ISeq {

    private final List<Expression> expressions;

    public LispExecutableList(List<Expression> expressions) {
        this.expressions = expressions;
    }

    /**
     * Evaluate list. During list evaluation the first element of the list is treated as an expression
     * which evaluates into a functions and rest elements are treated as that function's arguments.
     *
     * It is impossible to apply list itself to anything, but function may be result of list evaluation.
     * 
     * @param context execution context (map from variable names to instances of Expression)
     * @return evaluation result
     */
    @Override
    public Expression evaluate(Context context) {
        var copy = new LinkedList<>(expressions);
        var function = copy.remove(0).evaluate(context);
        return function.apply(context, copy);
    }

    /**
     * Lists are considered equal if all theirs elements are equal.
     * @param other
     * @return true if equal, false otherwise
     */
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

    @Override
    public String toString(){
        return expressions.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" ", "(", ")"));
    }

    /**
     * @return list length
     */
    public long size() {
        return expressions.size();
    }

    /**
     * @param index
     * @return element on position index
     */
    public Expression get(int index) {
        return expressions.get(index);
    }

    /**
     * @return wrapped list of expression
     */
    public List<Expression> asList() {
        return expressions;
    }

    /**
     * @return the first element of list or null if list is empty
     */
    @Override
    public Expression first() {
        return expressions.isEmpty() ? null : expressions.get(0);
    }

    /**
     * @return list without the first element or null if list is empty or consists of single element
     */
    @Override
    public ISeq next() {
        return expressions.size() <= 1 ?
                null : new LispExecutableList(expressions.stream().skip(1).collect(Collectors.toList()));
    }
}
