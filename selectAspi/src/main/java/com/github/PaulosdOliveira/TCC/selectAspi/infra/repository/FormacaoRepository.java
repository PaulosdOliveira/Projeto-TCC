package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface FormacaoRepository extends JpaRepository<Formacao, UUID> {

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO(f.instituicao, f.curso, f.nivel, f.inicio, f.fim) from Formacao f where f.candidato.id = :idcandidato")
    List<FormacaoDTO> buscarFormacoesCandidato(Long idCandidato);


    @Transactional
    @Modifying
    @Query("Delete from Formacao f where f.id = :ididFormacao and f.candidato.id = :idCandidato")
    void deletarFormacao(UUID idFormacao, Long idCandidato);

}
