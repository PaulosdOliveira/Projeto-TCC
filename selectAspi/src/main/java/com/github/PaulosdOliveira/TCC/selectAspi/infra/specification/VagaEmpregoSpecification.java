package com.github.PaulosdOliveira.TCC.selectAspi.infra.specification;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class VagaEmpregoSpecification {

    public static Specification<VagaEmprego> stringLike(String campo, String valor) {
        return ((root, query, cb) ->
                cb.like(root.get(campo), "%" + valor + "%"));
    }


    public static Specification<VagaEmprego> stringEqual(String campo, String valor) {
        return ((root, query, cb) ->
                cb.equal(root.get(campo), valor));
    }

    public static Specification<VagaEmprego> exclusivaPcd(boolean isPcd) {
        return ((root, query, cb) ->
                cb.equal(root.get("exclusivoParaPcd"), isPcd));
    }

    public static Specification<VagaEmprego> notSexoExclusivo(Sexo sexo) {
        return ((root, query, cb) ->
                cb.notEqual(root.get("exclusivoParaSexo"), sexo.equals(Sexo.MASCULINO) ? Sexo.FEMININO : Sexo.MASCULINO));

    }

    public static Specification<VagaEmprego> isAtiva() {
        return (root, query, cb) ->
                cb.equal(root.get("vagaAtiva"), true);
    }

    public static Specification<VagaEmprego> dateIsValid() {
        return (root, query, cb) ->
                cb.greaterThan(root.get("dataHoraEncerramento"), LocalDateTime.now());
    }

    public static Specification<VagaEmprego> foreignKeyIgual(String foreign, String id) {
        return (root, query, cb) ->
                cb.equal(root.get(foreign).get("id"), Integer.parseInt(id));
    }


}
