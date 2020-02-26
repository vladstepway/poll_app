package com.stepovoy.exception;

public class CommonAppException extends RuntimeException {
    private String message;

    public CommonAppException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
