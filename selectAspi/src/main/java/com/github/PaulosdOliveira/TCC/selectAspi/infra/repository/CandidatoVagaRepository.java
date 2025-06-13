package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CandidatoVaga;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CandidaturaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

public interface CandidatoVagaRepository extends JpaRepository<CandidatoVaga, CandidaturaPK> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "Insert into candidato_a_vaga (candidato_id, vaga_emprego_id, data_candidatura) " +
                                       "values (:idCandidato, :idVaga, :dataCandidatura)")
    void cadastarCandidatura(Long idCandidato, Long idVaga, LocalDateTime dataCandidatura);


    @Query("Select c from CandidatoVaga c where c.candidatura.candidato.id = :idCandidato and c.candidatura.vaga.id = :idVaga")
    Optional<CandidatoVaga> candidaturaExiste(@Param("idCandidato") Long idCandidato, @Param("idVaga") Long idVaga);
}
