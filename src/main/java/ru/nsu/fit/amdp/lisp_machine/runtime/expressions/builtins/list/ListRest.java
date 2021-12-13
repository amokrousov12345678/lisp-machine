package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.list;

import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.datatypes.ISeq;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispExecutableList;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.builtins.BuiltinOperation;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * rest implementation
 */
public class ListRest extends BuiltinOperation {

    /**
     * Get the tail of provided sequence (sequence without the first element).
     *
     * @param args {@link ISeq sequence}
     * @return sequence without the first element if it is not empty, otherwise empty list
     *
     * @throws IllegalArgumentException if args.size != 1 or args[0] is not a {@link ISeq sequence}
     */
    @Override
    public Expression execute(List<Expression> args) {
        if(args.size() != 1)
            throw new IllegalArgumentException("Incorrect amount of args for rest");

        if(!(args.get(0) instanceof ISeq))
            throw new IllegalArgumentException("Provided arg is not a sequence");

        var next = ((ISeq) args.get(0)).next();

        if (next == null)
            return new LispExecutableList(new LinkedList<>());

        return next;
    }

    @Override
    public String toString() {
        return "rest";
    }

}
