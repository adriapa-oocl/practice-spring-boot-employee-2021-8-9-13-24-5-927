package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {

//    private String message;
//
//    public EmployeeNotFoundException(String message){
//        super(message);
//    }

    @Override
    public String getMessage() {
        return "Employee id not found";
    }
}
