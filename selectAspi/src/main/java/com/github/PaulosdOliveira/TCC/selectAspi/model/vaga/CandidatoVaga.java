package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Entity
@Table(name = "candidato_a_vaga")
public class CandidatoVaga {


    @EmbeddedId
    private CandidaturaPK candidatura;

    @Column(nullable = false)
    private LocalDateTime dataCandidatura;

    public CandidatoVaga(CandidaturaPK candidatura, LocalDateTime dataCandidatura) {
        this.candidatura = candidatura;
        this.dataCandidatura = dataCandidatura;
    }
}
