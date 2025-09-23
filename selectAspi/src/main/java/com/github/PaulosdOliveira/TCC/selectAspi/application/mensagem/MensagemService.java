package com.github.PaulosdOliveira.TCC.selectAspi.application.mensagem;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.*;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.ChatContatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.PerfilRemetente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository repository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<MensagemDTO> carregarMensagens(String idDestinatario) {
        var login = SecurityContextHolder.getContext().getAuthentication();
        String idUsuarioLogado = login.getCredentials().toString();
        if (login.getAuthorities().toString().contains("empresa"))
            return repository.buscarMensagens(Long.parseLong(idDestinatario), UUID.fromString(idUsuarioLogado));
        return repository.buscarMensagens(Long.parseLong(idUsuarioLogado), UUID.fromString(idDestinatario));
    }

    public List<Contato> buscarChatsRecetes() {
        var login = SecurityContextHolder.getContext().getAuthentication();
        if (login.getAuthorities().toString().contains("empresa")) {
            System.out.println("Contém");
            return repository.buscarContatosRecentesEmpresa(UUID.fromString(login.getCredentials().toString()));
        }
        System.out.println("Não");
        var contatos = repository.buscarContatosRecentesCandidato(Long.parseLong(login.getCredentials().toString()));
        System.out.println(contatos);
        System.out.println(Long.parseLong(login.getCredentials().toString()));
        return contatos;
    }

    public ChatContatoDTO buscarDadosContato(String idContato) {
        var dados = new ChatContatoDTO();
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString()
                .contains("empresa")) dados = candidatoRepository.buscarDadosChat(Long.parseLong(idContato));
        else dados = empresaRepository.buscarDadosChatEmpresa(UUID.fromString(idContato));
        System.out.println(dados);
        return dados;
    }

    // VISUALIZANDO MENSAGENS RECEBIDAS
    public void visualizarMensagens(String idDestino) {
        String idUsuarioLogado = getIdAuthString();
        assert idUsuarioLogado != null;
        if (SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().toString().contains("empresa")) {
            repository.visualizarMensagens(UUID.fromString(idUsuarioLogado), Long.parseLong(idDestino), PerfilRemetente.EMPRESA);
        } else {
            repository.visualizarMensagens(UUID.fromString(idDestino), Long.parseLong(idUsuarioLogado), PerfilRemetente.CANDIDATO);
        }
        messagingTemplate.convertAndSend("/mensagem/visualizar/" + idUsuarioLogado, getMap("idContato", idDestino));
    }

    void visualizarMensagemrecebida(UUID idMensagem) {
        try {
            repository.visualizarMensagem(idMensagem, getIdCandidatoLogado());
        } catch (Exception e) {
            repository.visualizarMensagem(idMensagem, getIdEmpresaLogada());
        }
    }

    public List<String> getNotificacoes() {
        var id = getIdCandidatoLogado() != null ? getIdCandidatoLogado() : getIdEmpresaLogada();
        if (id instanceof Long) {
            return repository.qtdMensagensNaoLidas((Long) id).stream().map(UUID::toString).toList();
        }
        assert id != null;
        return repository.qtdMensagensNaoLidas((UUID) id).stream().map(Object::toString).toList();
    }

}
