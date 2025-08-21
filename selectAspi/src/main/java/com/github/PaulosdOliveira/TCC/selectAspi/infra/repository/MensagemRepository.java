package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO(m.id, m.texto, m.dataHoraEnvio) from Mensagem m where m.candidato.id = :idCandidato  and m.empresa.id = :idEmpresa order by m.dataHoraEnvio")
    List<MensagemDTO> buscarMensagens(Long idCandidato, UUID idEmpresa);


    @Query("""
            Select  new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato(m.candidato.id, m.candidato.nome, m.texto) from Mensagem m
             where m.dataHoraEnvio = (
              select max(m2.dataHoraEnvio) from Mensagem m2 where m2.empresa.id = m.empresa.id
              )
               and m.empresa.id = :idEmpresa
            """)
    List<Contato> buscarContatosRecentesEmpresa(UUID idEmpresa);


    @Query("""
            Select  new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato(m.empresa.id, m.empresa.nome, m.texto)
            from Mensagem m where  m.dataHoraEnvio = (
            select max(m2.dataHoraEnvio) from Mensagem m2 where m2.candidato.id = m.candidato.id
            )
            and m.candidato.id = :idCandidato
            """ )
    List<Contato> buscarContatosRecentesCandidato(Long idCandidato);

}
