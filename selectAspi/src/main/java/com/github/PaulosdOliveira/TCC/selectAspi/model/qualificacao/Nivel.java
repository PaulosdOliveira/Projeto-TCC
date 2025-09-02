package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import lombok.Getter;

@Getter
public enum Nivel {
    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");

    private final String texto;

    Nivel(String texto) {
        this.texto = texto;
    }
}
