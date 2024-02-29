package com.example.backend.EmployeeManagement.exception;

public class TimesheetNotFoundException extends RuntimeException{
    public TimesheetNotFoundException(String message) {
        super(message);
    }

}
