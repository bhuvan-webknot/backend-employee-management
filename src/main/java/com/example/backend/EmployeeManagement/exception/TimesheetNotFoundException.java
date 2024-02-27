package com.example.backend.EmployeeManagement.exception;

public class TimesheetNotFoundException extends Exception{
    public TimesheetNotFoundException() {
        super();
    }

    public TimesheetNotFoundException(String message) {
        super(message);
    }

    public TimesheetNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimesheetNotFoundException(Throwable cause) {
        super(cause);
    }

    protected TimesheetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
