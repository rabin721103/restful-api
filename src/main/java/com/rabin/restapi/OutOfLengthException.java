package com.rabin.restapi;

public class OutOfLengthException extends RuntimeException{

    public OutOfLengthException(String message) {
        super(message);
    }
}
