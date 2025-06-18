package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import static com.github.PaulosdOliveira.TCC.selectAspi.infra.specification.VagaEmpregoSpecification.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface VagaEmpregoRepository extends JpaRepository<VagaEmprego, Long>, JpaSpecificationExecutor<VagaEmprego> {


    default List<ConsultaVagaDTO> buscarVagas(String titulo, String estado, String cidade, Nivel senioridade,
                                              Sexo sexo, boolean isPcd, Modelo modelo) {
        Specification<VagaEmprego> spec = isAtiva()
                .and(notSexoExclusivo(sexo))
                .and(exclusivaPcd(isPcd));
        if (titulo != null) spec = spec.and(stringLike("titulo", titulo));
        if (StringUtils.isNotBlank(estado)) spec = spec.and(stringLike("estado", estado));
        if (StringUtils.isNotBlank(cidade)) spec = spec.and(stringLike("cidade", cidade));
        if (senioridade != null) spec = spec.and(stringEqual("nivel", senioridade.name()));
        if (modelo != null) spec = spec.and(stringEqual("modelo", modelo.name()));
        return findAll(spec).stream().map(ConsultaVagaDTO::new).toList();
    }

    default List<ConsultaVagaDTO> buscarVagasAlinhadas(List<String> qualificacoes) {
        Specification<VagaEmprego> spec = isAtiva();
        Specification<VagaEmprego> descricaoLike = Specification.where(null);
        for (String qualificacao : qualificacoes)
            descricaoLike = descricaoLike.or(stringLike("descricao", qualificacao));
        spec = spec.and(descricaoLike);
        return findAll(spec).stream().map(ConsultaVagaDTO::new).toList();
    }
}
