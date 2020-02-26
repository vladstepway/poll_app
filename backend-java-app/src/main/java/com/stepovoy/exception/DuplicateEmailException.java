package com.stepovoy.exception;

public class DuplicateEmailException extends RuntimeException {
    private String message;

    public DuplicateEmailException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
