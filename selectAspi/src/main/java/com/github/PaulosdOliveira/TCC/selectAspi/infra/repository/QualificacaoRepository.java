package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QualificacaoRepository extends JpaRepository<Qualificacao, Long> {

    default List<Qualificacao> buscarTudo(){
        Sort order = Sort.by("nome");
        return findAll(order);
    }

    @Query("Select q from Qualificacao q where q.nome like :nome%")
    List<Qualificacao> findByNomeLike(String nome);
}
