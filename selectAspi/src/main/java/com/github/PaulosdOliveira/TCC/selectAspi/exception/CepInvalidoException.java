package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class CepInvalidoException extends RuntimeException {
    public CepInvalidoException() {
        super("Erro: CEP Inválido!!!");
    }
}
