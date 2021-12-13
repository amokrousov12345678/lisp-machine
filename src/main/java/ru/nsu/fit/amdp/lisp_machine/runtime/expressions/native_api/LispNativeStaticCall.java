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
 * Java static method call implementation,
 */
@SuppressWarnings("unchecked")
public class LispNativeStaticCall implements Expression {

    /**
     * Call static method args[1] of class args[0] using args[2-N] as arguments.
     *
     * @param context execution context
     * @param args    list of arguments<ul>
     *                      <li>args[0] class name provided as {@link LispIdentifier}</li>
     *                      <li>args[1] static method name provided as {@link LispIdentifier}</li>
     *                      <li>args[2-N] used as arguments</li></ul>
     *
     * @return result of static method args[1] of class invocation wrapped inside {@link LispObject}.
     */
    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("Invalid arg count");
        }

        String targetClassName = ((LispIdentifier) args.remove(0)).getName();
        String methodName = ((LispIdentifier) args.remove(0)).getName();
        Object[] javaArgs = NativeUtils.evalArgsAndReturnForNativeCall(context, args);
        try {
            Class targetClass = Class.forName(targetClassName);
            Method method = NativeUtils.getMethod(targetClass, methodName, javaArgs);

            var invokeResult = method.invoke(null, javaArgs);

            if (invokeResult instanceof Integer) {
                return new LispObject(((Integer) invokeResult).longValue());
            } else if (invokeResult instanceof Float) {
                return new LispObject(((Float) invokeResult).doubleValue());
            }

            return new LispObject(invokeResult);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid class name for static call");
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can't call method");
        } catch (InvocationTargetException e) {
            throw new LispException(e.getTargetException());
        }
    }
}