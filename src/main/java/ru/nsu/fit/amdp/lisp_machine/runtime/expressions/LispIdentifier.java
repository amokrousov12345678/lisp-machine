package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.UnknownIdentifierError;

import java.util.Objects;

public class LispIdentifier implements Expression{
    private final String name;

    public LispIdentifier(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LispIdentifier that = (LispIdentifier) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Expression evaluate(Context context) {
       var value = context.getByName(this);
       if(value.isPresent())
           return value.get();

       throw new UnknownIdentifierError(this.toString());
    }
}
