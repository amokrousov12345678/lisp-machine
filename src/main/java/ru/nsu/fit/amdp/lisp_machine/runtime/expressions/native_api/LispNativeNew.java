package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@SuppressWarnings("unchecked")
public class LispNativeNew implements Expression {

    @Override
    public Expression apply(Context context, List<Expression> args) {
        if (args.size() < 1) {
            throw new IllegalArgumentException("Invalid arg count");
        }
        String className = ((LispIdentifier) args.remove(0)).getName();
        Object[] javaArgs = NativeUtils.evalArgsAndReturnForNativeCall(context, args);

        try {
            Class createdClass = Class.forName(className);
            Constructor constructor = NativeUtils.getConstructor(createdClass, javaArgs);
            return new LispObject(constructor.newInstance(javaArgs));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid class name for new");
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Can't call constructor");
        }
    }
}