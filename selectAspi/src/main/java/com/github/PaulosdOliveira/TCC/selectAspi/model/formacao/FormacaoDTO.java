package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import lombok.Data;
import java.util.Date;


@Data
public class FormacaoDTO {
    private String instituicao;
    private String curso;
    private String nivel;
    private Date inicio;
    private Date fim;


    public FormacaoDTO(String instituicao, String curso, Nivel nivel, Date inicio, Date fim) {
        this.instituicao = instituicao;
        this.curso = curso;
        this.nivel = nivel.getTexto();
        this.inicio = inicio;
        this.fim = fim;
    }
}
