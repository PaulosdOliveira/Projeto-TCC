package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ChaveCompostaQualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacoesSalvas;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface QualificacaoUsuarioRepository extends JpaRepository<QualificacaoUsuario, ChaveCompostaQualificacao> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "Insert into qualificacao_usuario (candidato_id, qualificacao_id, nivel)" +
                    "values(:idCandidato, :idQualificacao, :nivel)")
    void insert(Long idCandidato, Long idQualificacao, String nivel);

    @Query("Select q.id.qualificacao.nome from QualificacaoUsuario q where q.id.candidato.id = :id")
    List<String> getQualifcacoesById(@Param("id") Long id);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacoesSalvas(q.id.qualificacao.nome,q.nivel) from QualificacaoUsuario q where q.id.candidato.id = :idCandidato ")
    List<QualificacoesSalvas> buscarQualificacoesPerfil(@Param("idCandidato") Long idCandidato);


}
