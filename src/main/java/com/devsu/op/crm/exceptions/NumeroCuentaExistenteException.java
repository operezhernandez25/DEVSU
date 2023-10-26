package com.devsu.op.crm.exceptions;


public class NumeroCuentaExistenteException extends RuntimeException {

    public NumeroCuentaExistenteException(String mensaje) {
        super(mensaje);
    }
}