package com.example.restaurant.error;

public class InvalidProductOperationException extends Exception {

    public InvalidProductOperationException(String message) {
        super(message);
    }
}
