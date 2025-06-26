package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class RazaoSocialDuplicadoException extends RuntimeException {
    public RazaoSocialDuplicadoException() {
        super("Já existe uma empresa cadastrada com esse nome");
    }
}
