package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.UnknownIdentifierError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Objects;

public class LispIdentifier implements Expression {
    private final String name;

    /**
     * Creates identifier with provided name
     * @param name
     */
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

    /**
     * @return name of LispIdentifier
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @return name of LispIdentifier
     */
    public String getName() {
        return name;
    }

    /**
     * @param context execution context (map from variable names to instances of Expression)
     * @return value of variable in provided context or throws UnknownIdentifierError
     */
    @Override
    public Expression evaluate(Context context) {
       var value = context.getByName(this);
       if(value.isPresent())
           return value.get();

       throw new UnknownIdentifierError(this.toString());
    }
}
