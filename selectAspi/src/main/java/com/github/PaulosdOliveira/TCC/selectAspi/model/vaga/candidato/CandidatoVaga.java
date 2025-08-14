package com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@Entity
@Table(name = "candidato_a_vaga")
@EntityListeners(AuditingEntityListener.class)
public class CandidatoVaga {


    @EmbeddedId
    private CandidaturaPK candidatura;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime dataCandidatura;

    @Enumerated(EnumType.STRING)
    private StatusCandidatura status;


    // Construtor para salvar candidatura
    public CandidatoVaga(Long idCandidato, Long idVaga) {
        this.setCandidatura(new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga)));
        this.status = StatusCandidatura.EM_ANALISE;
    }


}
