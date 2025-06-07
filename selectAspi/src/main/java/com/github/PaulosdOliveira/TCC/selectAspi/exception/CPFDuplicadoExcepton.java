package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class CPFDuplicadoExcepton extends RuntimeException {
    public CPFDuplicadoExcepton() {
        super("Esse CPF já esta cadastrado");
    }
}
