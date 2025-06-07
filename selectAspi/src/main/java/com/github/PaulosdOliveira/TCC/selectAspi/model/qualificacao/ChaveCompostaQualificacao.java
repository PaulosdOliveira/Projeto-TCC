package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class ChaveCompostaQualificacao {

    @JoinColumn
    @ManyToOne
    private Candidato candidato;

    @JoinColumn
    @ManyToOne
    private Qualificacao qualificacao;
}
