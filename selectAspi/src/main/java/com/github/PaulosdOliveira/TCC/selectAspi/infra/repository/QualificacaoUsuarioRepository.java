package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ChaveCompostaQualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QualificacaoUsuarioRepository extends JpaRepository<QualificacaoUsuario, ChaveCompostaQualificacao> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "Insert into qualificacao_usuario (id_candidato, id_qualificacao, nivel)" +
                    "values(:idCandidato, :idQualificacao, :nivel)" )
    void insert(Long idCandidato, Long idQualificacao, Nivel nivel);
}
