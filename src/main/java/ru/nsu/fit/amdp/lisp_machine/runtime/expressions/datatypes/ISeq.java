package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

public interface ISeq extends Expression{
    Expression first();
    ISeq next();
}
