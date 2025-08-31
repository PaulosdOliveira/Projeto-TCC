package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDeProposta;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDePropostaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModeloDePropostaRepository extends JpaRepository<ModeloDeProposta, UUID> {

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta.ModeloDePropostaDTO(m.id, m.titulo, m.descricao) from ModeloDeProposta m where m.empresa.id = :idEmpresa")
    List<ModeloDePropostaDTO> buscarRascunhos(UUID idEmpresa);
}
