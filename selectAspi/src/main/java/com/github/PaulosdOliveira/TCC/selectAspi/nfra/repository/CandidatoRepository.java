package com.github.PaulosdOliveira.TCC.selectAspi.nfra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    @Query("Update c Candidato c set c.foto = :foto where c.id = :id")
    void salvarFotoCandidato(@Param("id") Long id, @Param("foto") byte[] foto);

    @Query("Update c Candidato c set c.curriculo = :curriculo where c.id = :id")
    void salvarCurriculoCandidato(@Param("id") Long id, @Param("curriculo") byte[] curriculo);
}
