package com.thoughtworks.springbootemployee.advice;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String message;
    private HttpStatus statusCode;

    public ErrorResponse(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}