package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException() {
        super("Esse email já está sendo usado");
    }
}
