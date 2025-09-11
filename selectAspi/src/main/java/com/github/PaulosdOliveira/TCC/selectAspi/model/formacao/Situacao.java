package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import lombok.Getter;

@Getter
public enum Situacao {
    EM_ANDAMENTO("Em andamento"),
    CONCLUIDO("Concluído");

    private final String texto;

    Situacao(String texto) {
        this.texto = texto;
    }
}
