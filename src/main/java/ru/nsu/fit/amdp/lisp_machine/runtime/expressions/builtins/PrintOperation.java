package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;

import java.util.stream.Collectors;

public class PrintOperation extends BuiltinOperation{
    @Override
    public Expression execute() {
        var printedString = getArgs().stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(" "));

        System.out.println(printedString);

        // Never call result of print, I guess ))
        // Можно будет на ексептион поменять
        return null;
    }
}
