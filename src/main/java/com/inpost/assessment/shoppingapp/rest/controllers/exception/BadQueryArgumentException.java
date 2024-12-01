package com.inpost.assessment.shoppingapp.rest.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadQueryArgumentException extends Exception {
    public BadQueryArgumentException(String message) {
        super(message);
    }
}
