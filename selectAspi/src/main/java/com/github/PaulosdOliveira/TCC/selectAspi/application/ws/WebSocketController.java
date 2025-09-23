package com.github.PaulosdOliveira.TCC.selectAspi.application.ws;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Contato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.CadastroMensagemDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.MensagemDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getMap;
import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdAuthString;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final MensagemRepository repository;
    private final SimpMessagingTemplate templete;
    private final CandidatoRepository candidatoRepository;
    private final EmpresaRepository empresaRepository;

    @MessageMapping("/receber-mensagem/{self}/{target}")
    // @SendTo("/mensagem/enviar-mensagem/{destino}")
    public void receberMensagem(@DestinationVariable("target") String target,
                                @DestinationVariable("self") String self,
                                CadastroMensagemDTO mensagem, Principal principal) {
        Authentication auth = (Authentication) principal;
        System.out.println(mensagem.getTexto());
        var mensagemSalva = repository.save(new Mensagem(mensagem));
        System.out.println(target);
        // ENVIANDO MENSAGEM PARA CONEXÃO DE CHAT
        templete.convertAndSend("/mensagem/enviar-mensagem/" + self + target, new MensagemDTO(mensagemSalva.getId(),
                mensagemSalva.getTexto(), mensagemSalva.getDataHoraEnvio(), mensagemSalva.getEmpresa().getId(),
                mensagemSalva.getCandidato().getId(), mensagemSalva.getPerfilRemetente()));

        System.out.println(mensagemSalva);

        // ENVIANDO NOTIFICACAO AO USUÁRIO
        // VERIFICANDO SE É UMA EMPRESA QUE ESTÁ ENVIANDO A MENSAGEM
        boolean isEmpresa = auth.getAuthorities().toString().contains("empresa");
        Contato contato;
        if (auth.getAuthorities().toString().contains("empresa")) {
            var empresa = empresaRepository.findById(mensagemSalva.getEmpresa().getId()).orElseThrow();
            contato = new Contato(empresa.getId(), empresa.getNome(), mensagemSalva.getTexto(), 1L);
        } else {
            var candidato = candidatoRepository.findById(mensagemSalva.getCandidato().getId()).orElseThrow();
            contato = new Contato(candidato.getId(), candidato.getNome(), mensagemSalva.getTexto(), 1L);
        }

        templete.convertAndSend("/mensagem/enviar-mensagem/" + target, contato);
    }
}
