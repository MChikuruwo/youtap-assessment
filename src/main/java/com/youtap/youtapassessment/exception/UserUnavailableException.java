package com.youtap.youtapassessment.exception;

public class UserUnavailableException extends RuntimeException {

    public UserUnavailableException() {
    }

    public UserUnavailableException(String message) {
        super(message);
    }

    public UserUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserUnavailableException(Throwable cause) {
        super(cause);
    }

    public UserUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}