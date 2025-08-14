package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
@Service
public class LocalizacaoService {

    private final WebClient webClient = WebClient.create("https://viacep.com.br/ws");

    @Deprecated
    public Localizacao buscarLocalizacaoPorCep(String cep) {
        try {
            System.out.println("Chegou aquuii no CEP");
            return webClient.get()
                    .uri("/{cep}/json", cep)
                    .retrieve()
                    .bodyToMono(Localizacao.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new CepInvalidoException();
        }
    }


}
