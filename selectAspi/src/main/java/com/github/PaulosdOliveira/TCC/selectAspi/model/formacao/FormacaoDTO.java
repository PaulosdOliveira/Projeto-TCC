package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import lombok.Data;
import java.util.UUID;


@Data
public class FormacaoDTO {
    private UUID id;
    private String instituicao;
    private String curso;
    private String nivel;
    private String situacao;



    public FormacaoDTO(UUID id, String instituicao, String curso, Nivel nivel, Situacao situacao) {
        this.id = id;
        this.instituicao = instituicao;
        this.curso = curso;
        this.nivel = nivel.getTexto();
        this.situacao = situacao.getTexto();
    }


}
