package ru.nsu.fit.amdp.lisp_machine.runtime.expressions;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.NotCallableObjectError;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.LispPersistentList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class LispMacroExpression implements Expression {
    private final LispFunction lispFunction;

    public LispMacroExpression(LispFunction lispFunction) {
        this.lispFunction = lispFunction;
    }

    public Expression apply(Context context, List<Expression> args) {
        /*List<Expression> funArgs = args.stream().map(arg -> new LispQuote().apply(nullptr, List.of(arg)));
        List<Expression> funArgs = new LinkedList<>();
        funArgs.add(new LispObject(new LispPersistentList(args)));
        lispFunction.setArgs(funArgs);
        var result = lispFunction.execute();
        if (result instanceof LispPersistentList) {
            return new LispExecutableList((LispPersistentList) result);
        }
        throw new RuntimeException("Macro must return PersistentList");*/
    }

}

