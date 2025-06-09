package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.chave.ChaveSecreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecretKeyRepository extends JpaRepository<ChaveSecreta, Integer> {

    @Query(nativeQuery = true, value = "Select * from chave_secreta order by data_criacao limit 1")
    ChaveSecreta buscarChave();
}
