package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums;

import lombok.Getter;

@Getter
public enum StatusCandidatura {
    EM_ANALISE("Em análise"),
    SELECIONADO("Selecionado"),
    DISPENSADO("Dispensado"),
    ;

    private final String texto;

    StatusCandidatura(String texto) {
        this.texto = texto;
    }
}
