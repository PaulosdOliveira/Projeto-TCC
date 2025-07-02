package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class IdadeBaixaException extends RuntimeException {
    public IdadeBaixaException() {
        super("Você precisa ter no mínimo 16 anos");
    }
}
