package ru.nsu.fit.amdp.lisp_machine.runtime.exceptions;

public class LispException extends RuntimeException {
    Throwable innerException;
    public LispException(Throwable innerException) {
        this.innerException = innerException;
    }

    public Throwable getInnerException() {
        return innerException;
    }

    public static Throwable unwrap(Throwable t) {
        return (t instanceof LispException) ? ((LispException) t).innerException : t;
    }
}
