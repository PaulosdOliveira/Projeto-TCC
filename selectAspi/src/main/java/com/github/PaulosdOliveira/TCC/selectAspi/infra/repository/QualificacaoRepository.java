package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificacaoRepository extends JpaRepository<Qualificacao, Long> {
}
