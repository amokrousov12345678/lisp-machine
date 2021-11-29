package ru.nsu.fit.amdp.lisp_machine.runtime;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.LispContext;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.*;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.PrintOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispEquals;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.comparators.LispLess;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.CreateList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListConcat;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListFirst;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list.ListRest;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.LispAnd;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.LispNot;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.logic.LispOr;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.Add;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.Div;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.Mult;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.math.Sub;

import java.util.List;

public class Machine {

    private Context context;

    public Machine(){
        context = new LispContext();

        // TODO: move context initialization elsewhere

        context.define(new LispIdentifier("+"), new Add());
        context.define(new LispIdentifier("-"), new Sub());
        context.define(new LispIdentifier("*"), new Mult());
        context.define(new LispIdentifier("/"), new Div());
        context.define(new LispIdentifier("print"), new PrintOperation());
        context.define(new LispIdentifier("def"), new LispDefine());
        context.define(new LispIdentifier("fn"), new LispFn());
        context.define(new LispIdentifier("list"), new CreateList());
        context.define(new LispIdentifier("first"), new ListFirst());
        context.define(new LispIdentifier("rest"), new ListRest());
        context.define(new LispIdentifier("concat"), new ListConcat());

        var eq = new LispEquals();
        context.define(new LispIdentifier("=="), eq);
        context.define(new LispIdentifier("="), eq);
        context.define(new LispIdentifier("eq"), eq);

        context.define(new LispIdentifier("<"), new LispLess());
        context.define(new LispIdentifier("!"), new LispNot());
        context.define(new LispIdentifier("and"), new LispAnd());
        context.define(new LispIdentifier("or"), new LispOr());
    }

    public void eval(List<LispExecutableList> program) {
        for (var instruction : program) {
            instruction.evaluate(context);
        }
    }

}
