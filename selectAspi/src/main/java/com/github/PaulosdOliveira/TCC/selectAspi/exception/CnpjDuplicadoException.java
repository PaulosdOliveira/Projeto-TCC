package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class CnpjDuplicadoException extends RuntimeException {
    public CnpjDuplicadoException() {
        super("Esse CPNJ já está cadastrado");
    }
}
