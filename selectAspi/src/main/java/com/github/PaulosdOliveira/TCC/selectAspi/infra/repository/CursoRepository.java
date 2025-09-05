package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoComplementar;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CursoRepository extends JpaRepository<CursoComplementar, UUID> {


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO(c.id, c.instituicao, c.curso, c.cargaHoraria) from CursoComplementar c where c.candidato.id = :idCandidato")
    List<CursoDTO> buscarCursosCandidato(Long idCandidato);


    @Transactional
    @Modifying
    @Query("Delete from CursoComplementar c where c.id = :idCurso and c.candidato.id = :idCandidato")
    void deletarCurso(UUID idCurso, Long idCandidato);
}
