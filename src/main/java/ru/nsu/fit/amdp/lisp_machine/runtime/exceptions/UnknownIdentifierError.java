package ru.nsu.fit.amdp.lisp_machine.runtime.exceptions;

public class UnknownIdentifierError extends RuntimeException {

    private final String id;

    public UnknownIdentifierError(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UnknownIdentifierError{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public String getMessage() {
        return id + " is not defined " + super.getMessage();
    }
}
