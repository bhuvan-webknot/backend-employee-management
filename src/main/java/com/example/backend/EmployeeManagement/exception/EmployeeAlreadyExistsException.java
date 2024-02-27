package com.example.backend.EmployeeManagement.exception;

public class EmployeeAlreadyExistsException extends Exception{
    public EmployeeAlreadyExistsException() {
        super();
    }

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }

    public EmployeeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected EmployeeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
