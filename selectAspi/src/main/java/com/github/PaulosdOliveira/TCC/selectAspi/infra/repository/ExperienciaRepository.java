package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public interface ExperienciaRepository extends JpaRepository<Experiencia, UUID> {


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO(e.id, e.empresa, e.cargo, e.descricao, e.inicio, e.fim) from Experiencia e where e.candidato.id = :idCandidato")
    List<ExperienciaDTO> buscarExperienciasUsuario(@Param("idCandidato") Long idCandidato);

    @Modifying
    @Transactional
    @Query("Delete from Experiencia e where e.id = :id and e.candidato.id = :idProprietario")
    void deletarExperiencia(UUID id, Long idProprietario);
}
