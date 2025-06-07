package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @Query("Update  Candidato  set foto = :foto where id = :id")
    void salvarFotoCandidato(@Param("id") Long id, @Param("foto") byte[] foto);

    @Query("Update  Candidato  set curriculo = :curriculo where id = :id")
    void salvarCurriculoCandidato(@Param("id") Long id, @Param("curriculo") byte[] curriculo);
}
