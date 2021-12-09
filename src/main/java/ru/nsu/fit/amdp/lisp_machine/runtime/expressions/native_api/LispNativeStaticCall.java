package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@SuppressWarnings("unchecked")
public class LispNativeStaticCall implements Expression {

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
            return new LispObject(method.invoke(null, javaArgs));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid class name for static call");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Can't call method");
        }
    }
}