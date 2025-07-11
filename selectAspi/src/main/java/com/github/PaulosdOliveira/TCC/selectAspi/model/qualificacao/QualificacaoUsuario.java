package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class QualificacaoUsuario {

    @EmbeddedId
    private ChaveCompostaQualificacao id;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;


    public QualificacaoUsuario(ChaveCompostaQualificacao id, Nivel nivel) {
        this.id = id;
        this.nivel = nivel;
    }
}
