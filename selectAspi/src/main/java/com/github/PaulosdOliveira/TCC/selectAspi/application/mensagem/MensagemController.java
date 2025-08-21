package com.github.PaulosdOliveira.TCC.selectAspi.application.mensagem;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/contatos-recentes")
    public List<Contato> buscarChatsRecetes() {
        return service.buscarChatsRecetes();
    }



}
