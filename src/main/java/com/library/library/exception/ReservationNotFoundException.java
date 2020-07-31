package com.library.library.exception;

import org.springframework.core.NestedRuntimeException;

public class ReservationNotFoundException extends NestedRuntimeException {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
