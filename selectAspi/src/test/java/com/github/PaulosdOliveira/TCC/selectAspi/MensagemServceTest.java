package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.CadastroMensagemDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import java.util.UUID;

@SpringBootTest
public class MensagemServceTest {

    @Autowired
    private MensagemRepository repository;

    @Test
    void enviarMensagemCandidato() {
        repository.save(new Mensagem(new CadastroMensagemDTO("Grace Mandou a ultima", UUID.fromString("c2e355c0-5fa9-4387-bacd-a699cbb1515d"), 9L, "candidato")));
    }
}
