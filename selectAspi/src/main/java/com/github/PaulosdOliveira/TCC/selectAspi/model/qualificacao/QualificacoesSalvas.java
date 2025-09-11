package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class QualificacoesSalvas {

    private Long idQualificacao;
    private Long idCandidato;
    private String nome;
    private String nivel;

    public QualificacoesSalvas(QualificacaoUsuario qualificacaoUsuario) {
        this.idQualificacao = qualificacaoUsuario.getId().getQualificacao().getId();
        this.idCandidato = qualificacaoUsuario.getId().getCandidato().getId();
        this.nome = qualificacaoUsuario.getId().getQualificacao().getNome();
        this.nivel = qualificacaoUsuario.getNivel().getTexto();
    }
}
