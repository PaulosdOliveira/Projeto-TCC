package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.SecretKeyRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.chave.ChaveSecreta;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.PerfilRemetente;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class SelectAspiApplicationTests {

    @Autowired
    private LocalizacaoService service;

    @Autowired
    private SecretKeyRepository secretKeyRepository;

    @Autowired
    private MensagemRepository repository;


    @Test
    void contextLoads() {
        repository.visualizarMensagens(UUID.fromString("c2e355c0-5fa9-4387-bacd-a699cbb1515d"), 40L, PerfilRemetente.CANDIDATO);
    }

    @Test
    void webServiceTest() {
        System.out.println(service.buscarLocalizacaoPorCep("4409419"));
    }

    @Test
    void salvarChaveSecreta() {
        SecretKey secret = Jwts.SIG.HS256.key().build();
        ChaveSecreta chaveSecreta =
                new ChaveSecreta(null, secret.getEncoded(), secret.getAlgorithm(), LocalDateTime.now());
        secretKeyRepository.save(chaveSecreta);
    }

    @Test
    void buscarChaveSecretaTeste() {
        System.out.println(secretKeyRepository.buscarChave());
    }

}
