package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.exceptions.LispException;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispIdentifier;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * new implementation
 */
@SuppressWarnings("unchecked")
public class LispNativeNew implements Expression {

    /**
     * Create object of class provided int args[0] using args[1-N] as constructor arguments.
     *
     * @param context execution context
     * @param args    list of arguments<ul>
     *                      <li>args[0] class name provided as {@link LispIdentifier}</li>
     *                      <li>args[1-N] used as constructor arguments</li></ul>
     *
     * @return created object wrapped inside {@link LispObject}.
     */
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
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException("Can't call constructor");
        } catch (InvocationTargetException e) {
            throw new LispException(e.getTargetException());
        }
    }
}