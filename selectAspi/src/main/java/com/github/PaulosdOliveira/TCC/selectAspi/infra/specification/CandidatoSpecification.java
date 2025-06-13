package com.github.PaulosdOliveira.TCC.selectAspi.infra.specification;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CandidatoSpecification {


    public static Specification<Candidato> findByQualificacao(Long id, Nivel nivel) {
        return ((root, query, cb) -> {
            Join<Candidato, QualificacaoUsuario> quJoin = root.join("qualificacoes");
            Predicate idQualificacaoEqual = cb.equal(quJoin.get("id").get("qualificacao").get("id"), id);
            Predicate nivelEqual = cb.equal(quJoin.get("nivel"), nivel);
            return cb.and(idQualificacaoEqual, nivelEqual);
        });
    }
}
