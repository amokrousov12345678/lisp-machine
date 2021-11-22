package ru.nsu.fit.amdp.lisp_machine.runtime;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.context.LispContext;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.PrintOperation;
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

    }

    public void eval(List<LispExecutableList> program) {
        for (var instruction : program) {
            instruction.evaluate(context);
        }
    }

}
