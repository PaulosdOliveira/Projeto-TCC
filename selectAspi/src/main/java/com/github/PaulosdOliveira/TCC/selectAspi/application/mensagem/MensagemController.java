package com.github.PaulosdOliveira.TCC.selectAspi.application.mensagem;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.ChatContatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;


    @GetMapping("/{idDestinatario}")
    public List<MensagemDTO> carregarMensagens(@PathVariable String idDestinatario) {
        return service.carregarMensagens(idDestinatario);
    }

    @GetMapping("/dados/{idContato}")
    public ChatContatoDTO buscarDadosContato(@PathVariable String idContato) {
        return service.buscarDadosContato(idContato);
    }

    @GetMapping("/contatos-recentes")
    public List<Contato> buscarChatsRecetes() {
        return service.buscarChatsRecetes();
    }

    @PutMapping("/visualizar")
    public void visualizarMensagens(@RequestParam String idDestinatario) {
        service.visualizarMensagens(idDestinatario);
    }

    @PutMapping("/visualizar/{idMensagem}/{idRemetente}")
    public void visualizarMensagem(@PathVariable UUID idMensagem, @PathVariable String idRemetente) {
        service.visualizarMensagemrecebida(idMensagem, idRemetente);
    }

    @GetMapping("/nofificacoes")
    public List<String> getNofificacoes() {
        return service.getNotificacoes();
    }

}
