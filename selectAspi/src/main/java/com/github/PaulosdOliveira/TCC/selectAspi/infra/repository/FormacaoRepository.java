package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.OptionFormacaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface FormacaoRepository extends JpaRepository<Formacao, UUID> {

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO(f.id,f.instituicao, f.curso, f.nivel, f.situacao) from Formacao f where f.candidato.id = :idCandidato")
    List<FormacaoDTO> buscarFormacoesCandidato(Long idCandidato);


    @Transactional
    @Modifying
    @Query("Delete from Formacao f where f.id = :idFormacao and f.candidato.id = :idCandidato")
    void deletarFormacao(UUID idFormacao, Long idCandidato);


    @Query("Select distinct new com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.OptionFormacaoDTO(f.id, f.curso) from Formacao f where f.curso like :curso% group by f.curso")
    List<OptionFormacaoDTO> distinctCursosLikeString(String curso);
}
