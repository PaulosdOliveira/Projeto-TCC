package com.github.PaulosdOliveira.TCC.selectAspi.infra.repository;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<Mensagem, UUID> {


    @Query("Select DISTINCT new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.ChatContatoDTO(m.empresa.nome, m.empresa.id) from Mensagem m where m.empresa.id = :idEmpresa")
    ChatContatoDTO buscarDadosChatEmpresa(UUID idEmpresa);

    @Query("Select DISTINCT new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.ChatContatoDTO(m.candidato.nome, m.candidato.id) from Mensagem m where m.candidato.id = :idCandidato ")
    ChatContatoDTO buscarDadosChatCandidato(Long idCandidato);

    @Query("Select new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO(m.id, m.texto, m.dataHoraEnvio, m.empresa.id, m.candidato.id, m.perfilRemetente) from Mensagem m where m.candidato.id = :idCandidato  and m.empresa.id = :idEmpresa order by m.dataHoraEnvio DESC")
    List<MensagemDTO> buscarMensagens(Long idCandidato, UUID idEmpresa);


    @Query("""
            Select  new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato(m.candidato.id, m.candidato.nome, m.texto,
            (
            select count(m3.texto) from Mensagem m3 where m3.candidato.id = m.candidato.id and m3.empresa.id = :idEmpresa and m3.visualizado = false
             and m3.perfilRemetente = 'CANDIDATO'
            )
             ) from Mensagem m
             where m.dataHoraEnvio = (
              select max(m2.dataHoraEnvio) from Mensagem m2 where m2.candidato.id = m.candidato.id group by m.candidato.id
              )
               and m.empresa.id = :idEmpresa order by m.dataHoraEnvio
            """)
    List<Contato> buscarContatosRecentesEmpresa(UUID idEmpresa);


    @Query("""
            Select  new com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato(m.empresa.id, m.empresa.nome, m.texto,
            (
            select count(m3.texto) from Mensagem m3 where m3.empresa.id = m.empresa.id and m3.candidato.id = :idCandidato and m3.visualizado = false
             and m3.perfilRemetente = 'EMPRESA'
            )
            )
            from Mensagem m where  m.dataHoraEnvio = (
            select max(m2.dataHoraEnvio) from Mensagem m2 where m2.empresa.id = m.empresa.id and  m2.candidato.id = :idCandidato
            )
            and m.candidato.id = :idCandidato order by m.dataHoraEnvio
            """)
    List<Contato> buscarContatosRecentesCandidato(Long idCandidato);

    @Transactional
    @Modifying
    @Query("Update Mensagem m set m.visualizado = true where m.candidato.id = :idCandidato and m.empresa.id = :idEmpresa and m.perfilRemetente != :perfil")
    void visualizarMensagens(UUID idEmpresa, Long idCandidato, PerfilRemetente perfil);

    //Candidato vizualizando mensagem recebida
    @Transactional
    @Modifying
    @Query("Update Mensagem m set m.visualizado = true where m.id = :idMensagem")
    void visualizarMensagem(UUID idMensagem, Long idUsuario);

    //Empresa vizualizando mensagem recebida
    @Transactional
    @Modifying
    @Query("Update Mensagem m set m.visualizado = true where m.id = :idMensagem")
    void visualizarMensagem(UUID idMensagem, UUID idUsuario);


    // BUSCANDO QUANTIDADE DE MENSAGENS NÃO LIDAS
    @Query("Select DISTINCT m.empresa.id from Mensagem m where m.candidato.id = :idCandidato and m.perfilRemetente = 'EMPRESA' and m.visualizado = false")
    List<UUID> qtdMensagensNaoLidas(Long idCandidato);

    // BUSCANDO QUANTIDADE DE MENSAGENS NÃO LIDAS
    @Query("Select DISTINCT m.candidato.id from Mensagem m where m.empresa.id = :idEmpresa and m.perfilRemetente = 'CANDIDATO' and m.visualizado = false")
    List<Long> qtdMensagensNaoLidas(UUID idEmpresa);


}
