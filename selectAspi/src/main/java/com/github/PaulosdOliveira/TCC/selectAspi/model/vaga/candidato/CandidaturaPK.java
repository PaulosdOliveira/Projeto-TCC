package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CandidaturaPK {

    @JoinColumn
    @ManyToOne
    private Candidato candidato;

    @JoinColumn(name = "vaga_emprego_id")
    @ManyToOne
    private VagaEmprego vaga;
}
