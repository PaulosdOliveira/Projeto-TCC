package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    private CandidatoService service;

    @Test
    void cadastrarCandidatoTest() {
        CadastroCandidatoDTO cadastro = new CadastroCandidatoDTO();
        cadastro.setNome("Chelsea Hillary");
        cadastro.setCpf("098777777");
        cadastro.setCep("01552000");
        cadastro.setEmail("chelsea@oliveira");
        cadastro.setTel("11 998-1286");
        cadastro.setSenha("123");
        cadastro.setTrabalhando(false);
        cadastro.setDescricao("Nem sei oque estou fazendo");
        service.cadastrarUsuario(cadastro);
    }
}
