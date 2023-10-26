package com.devsu.op.crm.exceptions;

public class CuentaNotFoundException extends RuntimeException {
    
    public CuentaNotFoundException(String message) {
        super(message);
    }

    public CuentaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}