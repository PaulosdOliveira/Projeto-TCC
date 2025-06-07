package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class LocalizacaoService {

    private final WebClient webClient = WebClient.create("https://viacep.com.br/ws");

    public Localizacao buscarLocalizacaoPorCep(String cep){

        return webClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(Localizacao.class)
                .block();
    }
}
