package com.mutant.api.exception;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Date timestap;
    private final String message;
    private final String details;

    public ExceptionResponse(
            Date timestamp,
            String message,
            String details){
        this.timestap = timestamp;
        this.message = message;
        this.details = details;
    }
}
