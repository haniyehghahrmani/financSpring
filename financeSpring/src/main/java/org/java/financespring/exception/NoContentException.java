package org.java.financespring.exception;

public class NoContentException extends TemplateException{

    public NoContentException(String message) {
        super(message);
        setStatusCode(404);
    }
}
