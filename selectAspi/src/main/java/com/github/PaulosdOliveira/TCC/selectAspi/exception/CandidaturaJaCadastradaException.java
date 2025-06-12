package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class CandidaturaJaCadastradaException extends RuntimeException {
    public CandidaturaJaCadastradaException() {
        super("Você já se candidatou à essa vaga");
    }
}
