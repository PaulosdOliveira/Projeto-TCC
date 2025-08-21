package com.github.PaulosdOliveira.TCC.selectAspi.application.mensagem;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MensagemService {

    @Autowired
    private MensagemRepository repository;

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
        return repository.buscarContatosRecentesCandidato(Long.parseLong(login.getCredentials().toString()));
    }


}
