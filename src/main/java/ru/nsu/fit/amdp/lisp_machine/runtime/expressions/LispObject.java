package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;

import java.util.Objects;

public class LispObject implements Expression{
    private final Object object;

    public LispObject (Object object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LispObject that = (LispObject) o;
        return Objects.equals(object, that.object);
    }

    @Override
    public int hashCode() {
        return object.hashCode();
    }

    @Override
    public String toString() {
        return object.toString();
    }

    @Override
    public Expression evaluate(Context context) {
        return this;
    }
}
