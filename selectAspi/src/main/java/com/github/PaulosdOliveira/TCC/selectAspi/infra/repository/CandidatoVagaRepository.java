package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoVaga;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CandidatoVagaRepository extends JpaRepository<CandidatoVaga, CandidaturaPK> {


    @Query("Select c from CandidatoVaga c where c.candidatura.candidato.id = :idCandidato and c.candidatura.vaga.id = :idVaga")
    Optional<CandidatoVaga> candidaturaExiste(@Param("idCandidato") Long idCandidato, @Param("idVaga") Long idVaga);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO(c.candidatura.candidato.id, c.candidatura.candidato.nome, c.candidatura.candidato.cidade.nome, c.candidatura.candidato.estado.sigla, c.candidatura.candidato.dataNascimento, c.status) " +
           "from CandidatoVaga c where c.candidatura.vaga.id = :idVaga")
    List<CandidatoCadastradoDTO> buscarCandidatosVaga(@Param("idVaga") Long idVaga);

    @Modifying
    @Transactional
    @Query("update CandidatoVaga c set c.status = SELECIONADO where c.candidatura = :idCandidatura")
    void selecionarCandidato(@Param("idCandidatura") CandidaturaPK idCandidatura);

    @Modifying
    @Transactional
    @Query("update CandidatoVaga c set c.status = 'DISPENSADO' where c.candidatura = :idCandidatura")
    void dispensarCandidato(@Param("idCandidatura") CandidaturaPK idCandidatura);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato(c.candidatura.vaga.id, c.candidatura.vaga.titulo,c.candidatura.vaga.empresa.nome,c.candidatura.vaga.empresa.id, c.status) from CandidatoVaga c where c.candidatura.candidato.id = :idCandidato")
    List<CandidaturaCandidato> CandidaturasCandidato(@Param("idCandidato") Long idCandidato);
}
