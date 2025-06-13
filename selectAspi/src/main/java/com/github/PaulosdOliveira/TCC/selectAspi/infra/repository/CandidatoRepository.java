package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.LoginCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import org.springframework.data.domain.Sort;
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

public interface CandidatoRepository extends JpaRepository<Candidato, Long> , JpaSpecificationExecutor<Candidato> {

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


    @Query("Select c.curriculo from Candidato c where c.id = :id")
    byte[] buscarCurriculoCandidato(@Param("id") Long id);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.LoginCandidatoDTO(c.id, c.nome, c.email, c.senha)" +
            " from Candidato c where c.email = :cpfOuEmail  or c.cpf = :cpfOuEmail ")
    Optional<LoginCandidatoDTO> buscarCandidatoLogin(@Param("cpfOuEmail") String cpfOuEmail);



    default List<Candidato> findyCandidatoByQualificacao(List<ConsultaQualificacaoUsuario> qualificacoes){
        Specification<Candidato> spec = Specification.where(null);
        for(ConsultaQualificacaoUsuario q: qualificacoes){
            spec = spec.and(findByQualificacao(q.getIdQualificacao(), q.getNivel()));
        }
        //Sort ordem = Sort.by(Sort.Order.by("RAND()"));
        return findAll(spec);
    }

}
