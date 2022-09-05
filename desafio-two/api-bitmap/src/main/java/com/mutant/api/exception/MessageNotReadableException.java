package com.mutant.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageNotReadableException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MessageNotReadableException(String exception) {
        super(exception);
    }
}
