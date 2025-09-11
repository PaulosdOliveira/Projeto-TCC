package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import static com.github.PaulosdOliveira.TCC.selectAspi.infra.specification.CandidatoSpecification.*;

import java.util.List;
import java.util.Optional;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>, JpaSpecificationExecutor<Candidato> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @Transactional
    @Modifying
    @Query("Update  Candidato  set foto = :foto where id = :id")
    void salvarFotoCandidato(@Param("id") Long id, @Param("foto") byte[] foto);


    @Query("Select c.foto from Candidato c where c.id = :id")
    byte[] buscarFotoCandidato(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query("Update  Candidato  set curriculo = :curriculo where id = :id")
    void salvarCurriculoCandidato(@Param("id") Long id, @Param("curriculo") byte[] curriculo);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.EdicaoCandidatoDTO(c.nome, c.dataNascimento, c.descricao, " +
           " c.tel, c.pcd, c.trabalhando, c.sexo, c.cidade.id, c.estado.id) from Candidato c where c.id = :id")
    EdicaoCandidatoDTO buscarDadosSalvos(Long id);


    @Query("Select c.curriculo from Candidato c where c.id = :id")
    byte[] buscarCurriculoCandidato(@Param("id") Long id);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.LoginCandidatoDTO(c.id, c.nome, c.email, c.senha)" +
           " from Candidato c where c.email = :login  or c.cpf = :login")
    Optional<LoginCandidatoDTO> buscarCandidatoLogin(@Param("login") String login);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.PerfilCandidatoDTO(c.id, c.nome," +
           "c.descricao, c.tel, c.email, c.trabalhando, c.pcd, c.cidade, c.estado, c.dataNascimento) from Candidato c " +
           "where c.id = :id")
    Optional<PerfilCandidatoDTO> carregarPerfilCandidato(Long id);

    // Deescobrindo se candidato é PCD e qual é o seu sexo
    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosFitroVaga(c.sexo, c.pcd) from Candidato c where c.id = :id")
    DadosFitroVaga buscarDadosFiltroVaga(Long id);


    String findCpfById(Long id);

    default List<Candidato> findCandidatoByQualificacao(List<ConsultaQualificacaoUsuario> qualificacoes, String idEstado, String idCidade, String sexo, boolean isPcd, Boolean trabalhando) {
        Specification<Candidato> spec = is("pcd", isPcd);
        for (ConsultaQualificacaoUsuario q : qualificacoes) {
            spec = spec.and(findByQualificacao(q.getIdQualificacao(), q.getNivel()));
        }
        if (StringUtils.isNotBlank(idEstado)) spec = spec.and(foreignKeyIgual("estado", idEstado));
        if (StringUtils.isNotBlank(idCidade)) spec = spec.and(foreignKeyIgual("cidade", idCidade));
        if (StringUtils.isNotBlank(sexo)) spec = spec.and(stringEqual("sexo", sexo));
        if (trabalhando != null) spec = spec.and(is("trabalhando", trabalhando));
        spec = spec.and(orderByRandom());
        return findAll(spec);
    }


}
