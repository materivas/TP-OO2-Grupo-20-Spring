package com.oo2.grupo20.exceptions;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
