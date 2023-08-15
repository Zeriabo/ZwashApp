package com.zwash.exceptions;

public class NoNonExecutedBookingsFoundException extends RuntimeException {
    public NoNonExecutedBookingsFoundException(String message) {
        super(message);
    }
}
