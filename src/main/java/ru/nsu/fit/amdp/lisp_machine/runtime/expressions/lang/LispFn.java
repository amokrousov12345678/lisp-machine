package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang;


import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.functional.LispFunction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Function creator
 */
public class LispFn implements Expression {

    /**
     * @param context execution context
     * @param args    list of arguments:<ul>
     *                <li>args[0] should be a list of identifiers of function arguments.
     *                    Expression which name starts from '&' considered to be vararg</li>
     *                <li>args[1 - N] are expressions to be stored in function body</li></ul>
     * @return function of provided arguments and creates closure of passed execution context
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Too few args for fn");
        }

        var argnames = args.remove(0);
        if (!(argnames instanceof LispExecutableList)) {
            throw new IllegalArgumentException("No argument list provided");
        }

        if (!((LispExecutableList) argnames).asList().stream()
                .allMatch(a -> a instanceof LispIdentifier))
            throw new IllegalArgumentException("One of provided arguments names is invalid");

        var argnamesList = ((LispExecutableList) argnames).asList()
                .stream().map(a -> (LispIdentifier) a).collect(Collectors.toList());

        boolean isVarArg = false;
        if (argnamesList.size() > 0) {
            var lastArgName = argnamesList.get(argnamesList.size()-1);
            if (lastArgName.getName().charAt(0) == '&') {
                argnamesList.remove(lastArgName);
                argnamesList.add(new LispIdentifier(lastArgName.getName().substring(1)));
                isVarArg = true;
            }
        }
        return new LispFunction(context, args, argnamesList, isVarArg);

    }
}