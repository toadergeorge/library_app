package com.library.library.exception;

import org.springframework.core.NestedRuntimeException;

public class UserBookNotFoundException extends NestedRuntimeException {

    public UserBookNotFoundException(String message) {
        super(message);
    }
}
