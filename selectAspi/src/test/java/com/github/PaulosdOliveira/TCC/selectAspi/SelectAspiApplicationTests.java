package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@SpringBootTest
class SelectAspiApplicationTests {

    @Autowired
    private LocalizacaoService service;


    @Test
    void contextLoads() {

    }

    @Test
    void webServiceTest() {
        try {
            System.out.println(service.buscarLocalizacaoPorCep("4409418"));
        } catch (WebClientResponseException.BadRequest e) {
            throw new CepInvalidoException();
        }

    }

}
