package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Cidade;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.CidadeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.CidadeDTO(c.id, c.nome) from Cidade c where c.estado.id = :idEstado")
    List<CidadeDTO> buscarCidadesDeEstado(@Param("idEstado") int idEstado);

    @Query("Select c from Cidade c where c.id = :idCidade and c.estado.id = :idEstado")
    Optional<Cidade> validarCidade(int idCidade, int idEstado);
}
