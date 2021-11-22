package ru.nsu.fit.amdp.lisp_machine.runtime.exceptions;

public class NotCallableObjectError extends RuntimeException {
    private final String id;

    public NotCallableObjectError(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NotCallableObjectError{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return id + " is not callable " + super.getMessage();
    }
}
