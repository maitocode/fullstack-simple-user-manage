package com.example.usermanage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFounrdException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFounrdException(String message) {
        super(message);
    }
}
