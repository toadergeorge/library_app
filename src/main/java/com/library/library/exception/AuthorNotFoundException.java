package com.library.library.exception;

import org.springframework.core.NestedRuntimeException;

public class AuthorNotFoundException extends NestedRuntimeException {

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
