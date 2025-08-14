package com.github.PaulosdOliveira.TCC.selectAspi.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException() {
        super("Nenhum perfil encontrado");
    }
}
