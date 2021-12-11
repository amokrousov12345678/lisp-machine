package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Objects;

public class LispObject implements Expression {
    public static final LispObject nil = new LispObject(null);
    private final Object object;

    public LispObject (Object object) {
        this.object = object;
    }

    public Object self(){
        return object;
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
        return Objects.hashCode(object);
    }

    @Override
    public String toString() {
        return object == null ? "nil" : object.toString();
    }

    @Override
    public boolean asBool(){
        if (object instanceof Boolean)
            return (boolean) object;
        return true;
    }
}
