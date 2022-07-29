package com.danoth.todo.entity.exception;

public class InvalidUserIdException extends RuntimeException{

    public InvalidUserIdException(String message) {
        super(message);
    }
}
