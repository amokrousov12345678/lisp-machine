package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

public interface ISeq {
    Expression first();
    ISeq next();
    ISeq cons(ISeq other);
}
