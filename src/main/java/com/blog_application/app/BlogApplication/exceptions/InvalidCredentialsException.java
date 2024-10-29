package com.blog_application.app.BlogApplication.exceptions;

public class InvalidCredentialsException extends  RuntimeException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
