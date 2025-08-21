package com.github.PaulosdOliveira.TCC.selectAspi.application.ws;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.CadastroMensagemDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final MensagemRepository repository;

    @MessageMapping("/receber-mensagem")
    @SendTo("/mensagem/enviar-mensagem")
    public String receberMensagem( CadastroMensagemDTO mensagem) {
        System.out.println(mensagem.getTexto());
        repository.save(new Mensagem(mensagem));
        System.out.println(mensagem);
        return "Devolvendo a mensagem que recebi:  " + mensagem.getTexto();
    }
}
