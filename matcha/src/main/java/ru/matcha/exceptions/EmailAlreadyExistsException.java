package ru.matcha.exceptions;

public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException(String msg) {
        super(msg);
    }
}
