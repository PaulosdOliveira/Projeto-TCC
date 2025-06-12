package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class VagaEncerradaException extends RuntimeException {
    public VagaEncerradaException() {
        super("Essa vaga já foi encerrada");
    }
}
