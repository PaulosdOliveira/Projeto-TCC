package com.github.PaulosdOliveira.TCC.selectAspi.application.ws;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.CadastroMensagemDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final MensagemRepository repository;

    @MessageMapping("/receber-mensagem/{destino}")
    @SendTo("/mensagem/enviar-mensagem/{destino}")
    public MensagemDTO receberMensagem(@DestinationVariable("destino") String destino, CadastroMensagemDTO mensagem) {
        System.out.println(mensagem.getTexto());
        var mensagemSalva = repository.save(new Mensagem(mensagem));
        System.out.println(mensagem);
        return new MensagemDTO(mensagemSalva.getId(),mensagemSalva.getTexto(), mensagemSalva.getDataHoraEnvio(), mensagemSalva.getEmpresa().getId(), mensagemSalva.getCandidato().getId(), mensagemSalva.getPerfilRemetente());
    }
}
