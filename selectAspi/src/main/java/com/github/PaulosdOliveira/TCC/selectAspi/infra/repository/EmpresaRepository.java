package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
    boolean existsByNome(String nome);


    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO(e.id, e.nome, e.email, e.senha)" +
           " from Empresa e where e.email = :emailOrCnpj or e.cnpj = :emailOrCnpj")
    Optional<LoginEmpresaDTO> findByEmailOrCnpjLogin(@Param("emailOrCnpj") String emailOrCnpj);

    @Transactional
    @Modifying
    @Query("Update Empresa e set e.foto = :foto where e.id = :id")
    void salvarFoto(@Param("foto") byte[] foto, @Param("id") UUID id);

    @Query("Select e.foto from Empresa e where e.id = :id")
    byte[] buscarFotoPorId(@Param("id") UUID id);

    @Query("Select e.capa from Empresa e where e.id = :id")
    byte[] buscarCapaPorId(@Param("id") UUID id);
}
