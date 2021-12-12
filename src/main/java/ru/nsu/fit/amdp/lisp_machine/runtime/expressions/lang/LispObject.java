package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Objects;

/**
 * Common Expression wrapper for Java objects
 */
public class LispObject implements Expression {
    public static final LispObject nil = new LispObject(null);
    private final Object object;

    /**
     * Wraps provided object into LispObject
     * @param object
     */
    public LispObject (Object object) {
        this.object = object;
    }

    /**
     * @return wrapped Java object
     */
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

    /**
     * @return wrapped value if object instanceof Boolean, otherwise true
     */
    @Override
    public boolean asBool(){
        if (object instanceof Boolean)
            return (boolean) object;
        return true;
    }
}
