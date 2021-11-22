package ru.nsu.fit.amdp.lisp_machine.runtime.context;

import ru.nsu.fit.amdp.lisp_machine.parser.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.Optional;

public interface Context {

    Optional<Expression> getByName(LispIdentifier name);

    void define(LispIdentifier name, Expression value);

}
