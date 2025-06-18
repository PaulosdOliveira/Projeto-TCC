package com.github.PaulosdOliveira.TCC.selectAspi.application.ws;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final MensagemRepository repository;

    @MessageMapping("/receber-mensagem/{destino}")
    @SendTo("/enviar-mensagem/{destino}")
    public MensagemDTO receberMensagem(@DestinationVariable String destino, MensagemDTO mensagem) {
        repository.save(new Mensagem(mensagem));
        return mensagem;
    }
}
