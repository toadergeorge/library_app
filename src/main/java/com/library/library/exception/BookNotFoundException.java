package com.library.library.exception;

import org.springframework.core.NestedRuntimeException;

public class BookNotFoundException extends NestedRuntimeException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
