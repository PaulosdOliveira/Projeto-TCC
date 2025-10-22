package com.github.PaulosdOliveira.TCC.selectAspi.application.mensagem;

import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.ChatContatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;


    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/{idDestinatario}")
    public List<MensagemDTO> carregarMensagens(@PathVariable String idDestinatario) {
        return service.carregarMensagens(idDestinatario);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/dados/{idContato}")
    public ChatContatoDTO buscarDadosContato(@PathVariable String idContato) {
        return service.buscarDadosContato(idContato);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/contatos-recentes")
    public List<Contato> buscarChatsRecetes() {
        return service.buscarChatsRecetes();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/visualizar")
    public void visualizarMensagens(@RequestParam String idDestinatario) {
        service.visualizarMensagens(idDestinatario);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PutMapping("/visualizar/{idMensagem}/{idRemetente}")
    public void visualizarMensagem(@PathVariable UUID idMensagem, @PathVariable String idRemetente) {
        service.visualizarMensagemrecebida(idMensagem, idRemetente);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @GetMapping("/nofificacoes")
    public List<String> getNofificacoes() {
        return service.getNotificacoes();
    }

}
