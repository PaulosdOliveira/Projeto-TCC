package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import static com.github.PaulosdOliveira.TCC.selectAspi.infra.specification.VagaEmpregoSpecification.*;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VagaEmpregoRepository extends JpaRepository<VagaEmprego, Long>, JpaSpecificationExecutor<VagaEmprego> {


    default List<VagaEmprego> buscarVagas(String titulo, String estado, String cidade, String senioridade,
                                          Sexo sexo, boolean isPcd, String modelo, String tipo_contrato) {
        Specification<VagaEmprego> spec = isAtiva()
                .and(notSexoExclusivo(sexo))
                .and(exclusivaPcd(isPcd));
        if (titulo != null) spec = spec.and(stringLike("titulo", titulo))
                .or(stringLike("descricao", titulo));
        if (StringUtils.isNotBlank(estado)) spec = spec.and(stringLike("estado", estado));
        if (StringUtils.isNotBlank(cidade)) spec = spec.and(stringLike("cidade", cidade));
        if (StringUtils.isNotBlank(senioridade)) spec = spec.and(stringEqual("nivel", senioridade));
        if (StringUtils.isNotBlank(modelo)) spec = spec.and(stringEqual("modelo", modelo));
        if(StringUtils.isNotBlank(tipo_contrato)) spec = spec.and(stringEqual("tipoContrato", tipo_contrato));
        return findAll(spec, Sort.by("dataHoraPublicacao"));
    }

    default void convertToCard() {

    }

    default List<VagaEmprego> buscarVagasAlinhadas(List<String> qualificacoes) {
        Specification<VagaEmprego> spec = isAtiva();
        Specification<VagaEmprego> descricaoLike = Specification.where(null);
        for (String qualificacao : qualificacoes)
            descricaoLike = descricaoLike.or(stringLike("descricao", qualificacao));
        spec = spec.and(descricaoLike);
        return findAll(spec, Sort.by("dataHoraPublicacao").descending());
    }

    @Query("Select DISTINCT v.estado from VagaEmprego v")
    List<String> buscarEstados();

    @Query("Select distinct v.cidade from VagaEmprego v where v.estado like %:estado order by v.cidade")
    List<String> buscarCidades(String estado);
}
