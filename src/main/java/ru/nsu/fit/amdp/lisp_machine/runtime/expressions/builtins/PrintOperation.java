package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.List;
import java.util.stream.Collectors;

public class PrintOperation extends BuiltinOperation{
    @Override
    public Expression execute(List<Expression> args) {
        var printedString = args.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(" "));

        System.out.println(printedString);

        // Never call result of print, I guess ))
        // Можно будет на ексептион поменять
        return null;
    }
}
