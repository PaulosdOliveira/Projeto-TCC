package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import static com.github.PaulosdOliveira.TCC.selectAspi.infra.specification.VagaEmpregoSpecification.*;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmpresaDTO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface VagaEmpregoRepository extends JpaRepository<VagaEmprego, Long>, JpaSpecificationExecutor<VagaEmprego> {


    default List<VagaEmprego> buscarVagas(String titulo, String idEstado, String idCidade, String senioridade,
                                          String modelo, String tipo_contrato, Sexo sexoCandidatoLogado, boolean isPcd) {
        Specification<VagaEmprego> spec = isAtiva()
                .and(notSexoExclusivo(sexoCandidatoLogado))
                .and(exclusivaPcd(isPcd));
        System.out.println("Sexo: " + sexoCandidatoLogado + "Is PCD: " + isPcd);
        if (StringUtils.isNotBlank(titulo)) spec = spec.and(stringLike("titulo", titulo))
                .or(stringLike("descricao", titulo));
        if (StringUtils.isNotBlank(idEstado)) spec = spec.and(foreignKeyIgual("estado", idEstado));
        if (StringUtils.isNotBlank(idCidade)) spec = spec.and(foreignKeyIgual("cidade", idCidade));
        if (StringUtils.isNotBlank(senioridade)) spec = spec.and(stringEqual("nivel", senioridade));
        if (StringUtils.isNotBlank(modelo)) spec = spec.and(stringEqual("modelo", modelo));
        if (StringUtils.isNotBlank(tipo_contrato)) spec = spec.and(stringEqual("tipoContrato", tipo_contrato));
        return findAll(spec, Sort.by("dataHoraPublicacao").descending());
    }


    default List<VagaEmprego> buscarVagasAlinhadas(List<String> qualificacoes, Sexo sexo, boolean isPcd) {
        Specification<VagaEmprego> spec = isAtiva()
                .and(notSexoExclusivo(sexo))
                .and(exclusivaPcd(isPcd));
        Specification<VagaEmprego> descricaoLike = Specification.where(null);
        for (String qualificacao : qualificacoes)
            descricaoLike = descricaoLike.or(stringLike("descricao", qualificacao));
        spec = spec.and(descricaoLike);
        return findAll(spec, Sort.by("dataHoraPublicacao").descending());
    }

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego(v.id, v.vagaAtiva, v.dataHoraEncerramento) from VagaEmprego v where v.vagaAtiva = false")
    List<VagaEmprego> findAtivas();

    @Modifying
    @Query("Update VagaEmprego v set v.vagaAtiva = false where v.id = :id")
    void desativarVaga(@Param("id") Long id);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO(v) from VagaEmprego v where v.id = :id")
    Optional<ConsultaVagaDTO> carregarVaga(@Param("id") Long id);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmpresaDTO(v.id, v.titulo, v.dataHoraPublicacao, COUNT(c.id)) from VagaEmprego v left join CandidatoVaga c on c.id.vaga = v where v.empresa.id = :idEmpresa  group by v.id")
    List<VagaEmpresaDTO> buscarVagasEmpresa(@Param("idEmpresa") UUID idEmpresa);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO(v) from VagaEmprego v where v.id = :idVaga")
    Optional<CadastroVagaDTO> buscarDadosCadastrais(Long idVaga);
}
