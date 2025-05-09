package org.java.financespring.exception;


public class NoUserException extends TemplateException {

    public NoUserException(String message) {
        super(message);
        setStatusCode(404);
    }
}
