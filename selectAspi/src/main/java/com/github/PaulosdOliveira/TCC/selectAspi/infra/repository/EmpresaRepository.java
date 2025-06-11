package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByCnpj(String cnpj);

    boolean existsByEmail(String email);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO(e.id, e.nome, e.email, e.senha)" +
           " from Empresa e where e.email = :emailOrCnpj or e.cnpj = :emailOrCnpj")
    Optional<LoginEmpresaDTO> findByEmailOrCnpjLogin(@Param("emailOrCnpj") String emailOrCnpj);
}
