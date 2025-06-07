package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class QualificacaoUsuario {

    @EmbeddedId
    private ChaveCompostaQualificacao id;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;
}
