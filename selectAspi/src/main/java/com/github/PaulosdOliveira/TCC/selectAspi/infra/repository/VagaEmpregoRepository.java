package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import static com.github.PaulosdOliveira.TCC.selectAspi.infra.specification.VagaEmpregoSpecification.*;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface VagaEmpregoRepository extends JpaRepository<VagaEmprego, Long>, JpaSpecificationExecutor<VagaEmprego> {


    default List<VagaEmprego> buscarVagas(String titulo, String estado, String cidade, Nivel senioridade, Sexo sexo, boolean isPcd){
        Specification<VagaEmprego> spec = isAtiva()
                .and(notSexoExclusivo(sexo))
                .and(exclusivaPcd(isPcd));
        if(titulo != null) spec = spec.and(tituloLike(titulo));
        if(estado != null) spec = spec.and(estadoEqual(estado));
        if(cidade != null) spec = spec.and(cidadeEqual(cidade));
        if(senioridade != null) spec = spec.and(nivelEqual(senioridade));
        return findAll(spec);
    }
}
