package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Print implementation
 */
public class PrintOperation extends BuiltinOperation{
    /**
     * Print all passed expressions to stdout.
     * Be careful, since print automatically exhausts all
     * lazy sequences (including infinite ones).
     *
     * @param args evaluated expressions to be printed
     * @return nil
     */
    @Override
    public Expression execute(List<Expression> args) {
        var printedString = args.stream()
                                    .map(Object::toString)
                                    .collect(Collectors.joining(" "));

        System.out.println(printedString);

        return LispObject.nil;
    }
}
