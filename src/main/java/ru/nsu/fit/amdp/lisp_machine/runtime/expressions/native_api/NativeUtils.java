package ru.nsu.fit.amdp.lisp_machine.runtime.expressions.native_api;

import ru.nsu.fit.amdp.lisp_machine.runtime.context.Context;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.Expression;
import ru.nsu.fit.amdp.lisp_machine.runtime.expressions.lang.LispObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class NativeUtils {
    public static Object evalArgAndReturnForNativeCall(Context context, Expression arg) {
        Expression value = arg.evaluate(context);
        return value instanceof LispObject ? ((LispObject) value).self() : value;
    };
    public static Object[] evalArgsAndReturnForNativeCall(Context context, List<Expression> args) {
        return args.stream()
                .map(arg -> evalArgAndReturnForNativeCall(context, arg))
                .toArray();
    }

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

    public static boolean isPrimitiveWrapperOf(Class<?> targetClass, Class<?> primitive) {
        if (!primitive.isPrimitive()) {
            throw new IllegalArgumentException("First argument has to be primitive type");
        }
        return primitiveWrapperMap.get(primitive) == targetClass;
    }

    public static boolean isAssignable(Class from, Class to) {
        if (to.isAssignableFrom(from)) return true;
        if (from.isPrimitive()) return isPrimitiveWrapperOf(to, from);
        if (to.isPrimitive()) return isPrimitiveWrapperOf(from,to);
        return false;
    }

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
