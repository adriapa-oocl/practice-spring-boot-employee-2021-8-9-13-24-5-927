package com.thoughtworks.springbootemployee.exception;

public class CompanyNotFoundException extends RuntimeException{
    private String message;

    public CompanyNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
