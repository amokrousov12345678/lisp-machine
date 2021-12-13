package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.LispException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Java object's method call implementation,
 */
public class LispNativeCall implements Expression {

    /**
     * Call method with name args[0] of object args[1] using args[2-N] as arguments.
     *
     * @param context execution context
     * @param args    list of arguments<ul>
     *                      <li>args[0] method name provided as {@link LispIdentifier}</li>
     *                      <li>args[1] Java object wrapped in {@link LispObject}</li>
     *                      <li>args[2-N] used as arguments</li></ul>
     *
     * @return result of method args[0] invocation on object args[1] wrapped inside {@link LispObject}.
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Invalid arg count");
        }

        String methodName = ((LispIdentifier) args.remove(0)).getName();
        Object target = NativeUtils.evalArgAndReturnForNativeCall(context, args.remove(0));
        Object[] javaArgs = NativeUtils.evalArgsAndReturnForNativeCall(context, args);

        try {
            Method method = NativeUtils.getMethod(target.getClass(), methodName, javaArgs);
            var invokeResult = method.invoke(target, javaArgs);

            if (invokeResult instanceof Integer) {
                return new LispObject(((Integer) invokeResult).longValue());
            } else if (invokeResult instanceof Float) {
                return new LispObject(((Float) invokeResult).doubleValue());
            }

            return new LispObject(invokeResult);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can't call method");
        } catch (InvocationTargetException e) {
            throw new LispException(e.getTargetException());
        }
    }
}