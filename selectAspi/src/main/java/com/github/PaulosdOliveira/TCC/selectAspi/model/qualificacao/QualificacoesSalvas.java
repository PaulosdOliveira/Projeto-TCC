package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import lombok.Data;

@Data
public class QualificacoesSalvas {
    private String nome;
    private String nivel;

    public QualificacoesSalvas(String nome, Nivel nivel) {
        this.nome = nome;
        this.nivel = nivel.getTexto();
    }
}
