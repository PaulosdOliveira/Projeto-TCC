package com.github.PaulosdOliveira.TCC.selectAspi.infra.specification;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;

public class CandidatoSpecification {


    public static Specification<Candidato> findByQualificacao(Long id, Nivel nivel) {
        return ((root, query, cb) -> {
            Join<Candidato, QualificacaoUsuario> quJoin = root.join("qualificacoes");
            Predicate idQualificacaoEqual = cb.equal(quJoin.get("id").get("qualificacao").get("id"), id);
            Predicate nivelIN = nivel.equals(Nivel.AVANCADO) ? quJoin.get("nivel").in(nivel) : null;
            if (nivel.equals(Nivel.BASICO)) {
                nivelIN = quJoin.get("nivel").in(nivel, Nivel.INTERMEDIARIO.name(), Nivel.AVANCADO.name());
            } else if (nivel.equals(Nivel.INTERMEDIARIO)) {
                nivelIN = quJoin.get("nivel").in(nivel, Nivel.AVANCADO.name());
            }
            return cb.and(idQualificacaoEqual, nivelIN);
        });
    }


    public static Specification<Candidato> orderByRandom() {
        return (root, query, cb) -> {
            assert query != null;
            if (query.getOrderList().isEmpty()) {
                Expression<Double> expressionRandom = cb.function("RAND", Double.class);
                Order orderRandom = cb.asc(expressionRandom);
                query.orderBy(orderRandom);
            }
            return null;
        };
    }

    public static Specification<Candidato> stringEqual(String campo, String valor){
        return ((root, query, cb) ->
                cb.equal(root.get(campo), valor));
    }



}
