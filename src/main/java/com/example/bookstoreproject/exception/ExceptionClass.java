package com.example.bookstoreproject.exception;

public class ExceptionClass extends RuntimeException{
    private String message;

    public ExceptionClass(String message) {
        super(message);
        this.message =message;
    }
}
