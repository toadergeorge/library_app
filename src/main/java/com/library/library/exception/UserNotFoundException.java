package com.library.library.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFoundException extends NestedRuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
