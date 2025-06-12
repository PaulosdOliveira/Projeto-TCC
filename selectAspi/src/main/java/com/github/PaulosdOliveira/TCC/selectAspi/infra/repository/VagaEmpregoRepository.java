package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VagaEmpregoRepository extends JpaRepository<VagaEmprego, Long> {

}
