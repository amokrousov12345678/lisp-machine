package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Util class for native calls
 */
public class NativeUtils {

    /**
     * Evaluates expression and unwraps resulting object from {@link LispObject}.
     * If evaluated object is not an instance of {@link LispObject} returns
     * object itself without unwrapping.
     *
     * @param context context.
     * @param arg expression.
     * @return evaluation result unwrapped from {@link LispObject} wrapper (or object itself).
     */
    public static Object evalArgAndReturnForNativeCall(Context context, Expression arg) {
        Expression value = arg.evaluate(context);
        return value instanceof LispObject ? ((LispObject) value).self() : value;
    }

    /**
     * Calls {@link #evalArgAndReturnForNativeCall(Context, Expression)} on each arg from args.
     *
     * @param context context.
     * @param args arguments.
     * @return array of raw objects.
     */
    public static Object[] evalArgsAndReturnForNativeCall(Context context, List<Expression> args) {
        return args.stream()
                .map(arg -> evalArgAndReturnForNativeCall(context, arg))
                .toArray();
    }

    /**
     * Determine classes of provided objects
     *
     * @param objects array of objects
     * @return array of classes of provided objects
     */
    public static Class[] getArgsClasses(Object[] objects) {
        return  Stream.of(objects).map(Object::getClass).toArray(Class[]::new);
    }

    private static final Map<Class<?>, Class<?>> primitiveWrapperMap =
            Map.of(boolean.class, Boolean.class,
                    byte.class, Byte.class,
                    char.class, Character.class,
                    double.class, Double.class,
                    float.class, Float.class,
                    int.class, Integer.class,
                    long.class, Long.class,
                    short.class, Short.class);

    /**
     * Check whether provided class is a boxed integral type.
     *
     * @param targetClass target class
     * @param primitive class of integral type
     * @return true if target class is a boxed primitive integral type, otherwise false
     */
    public static boolean isPrimitiveWrapperOf(Class<?> targetClass, Class<?> primitive) {
        if (!primitive.isPrimitive()) {
            throw new IllegalArgumentException("First argument has to be primitive type");
        }
        return primitiveWrapperMap.get(primitive) == targetClass;
    }

    /**
     * Check whether it is possible to cast expression from
     * {@code from} to {@code to}
     *
     * @param from class to assign from
     * @param to class to assign to
     * @return true if<ul>
     *                      <li>{@code to} is assignable from {@code from}</li>
     *                      <li>{@code from} is an integral type and {@code to} is its box object wrapper and wise-versa</li></ul>
     */
    public static boolean isAssignable(Class from, Class to) {
        if (to.isAssignableFrom(from)) return true;
        if (from.isPrimitive()) return isPrimitiveWrapperOf(to, from);
        if (to.isPrimitive()) return isPrimitiveWrapperOf(from,to);
        return false;
    }

    /**
     * Check whether signature of parameterTypes is compatible with parameters
     *
     * @param parameterTypes array of expected parameter types
     * @param parameters array of objects
     * @return true if signature of parameterTypes is compatible with parameters, otherwise false.
     */
    public static boolean ifSignatureCompatibleWithArgTypes(Class<?>[] parameterTypes, Object[] parameters) {
        if (parameterTypes.length != parameters.length) return false;
        boolean matches = true;
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!isAssignable(parameters[i].getClass(), parameterTypes[i])) {
                matches = false;
                break;
            }
        }
        return matches;
    }

    /**
     * Attempts to find method {@code methodName} of object of class {@code c} which
     * consumes provided {@code parameters}.
     *
     * @param c class.
     * @param methodName method name.
     * @param parameters array of objects to be used as parameters.
     * @return method of class {@code c} which could be invoked with {@code parameters}.
     * @throws NoSuchMethodException if no method satisfies requirements.
     */
    public static Method getMethod(Class c, String methodName, Object[] parameters) throws NoSuchMethodException {
        for (Method method : c.getMethods()) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if ( ifSignatureCompatibleWithArgTypes(parameterTypes, parameters)) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }

    /**
     * Attempts to find a constructor of object of class {@code c}
     * which consumes provided {@code parameters}.
     *
     * @param c class.
     * @param parameters array of objects to be used as parameters.
     * @return constructor of object  of class {@code c} which could be invoked with {@code parameters}.
     * @throws NoSuchMethodException if no class {@code c} constructor satisfies requirements.
     */
    public static Constructor getConstructor(Class c, Object[] parameters) throws NoSuchMethodException {
        for (Constructor constructor : c.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if ( ifSignatureCompatibleWithArgTypes(parameterTypes, parameters)) {
                return constructor;
            }
        }
        throw new NoSuchMethodException();
    }
}
