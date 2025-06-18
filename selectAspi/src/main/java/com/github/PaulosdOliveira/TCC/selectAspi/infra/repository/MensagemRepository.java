package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {

    @Query("Select m from Mensagem m where m.idCandidato = :idCandidato  and m.idEmpresa = :idEmpresa order by m.dataHoraEnvio")
    List<Mensagem> buscarMensagens(Long idCandidato, UUID idEmpresa);
}
