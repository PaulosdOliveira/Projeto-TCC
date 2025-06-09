package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosLoginCandidatoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    private CandidatoService service;

    @Test
    void cadastrarCandidatoTest() throws Exception {
        CadastroCandidatoDTO cadastro = new CadastroCandidatoDTO();
        cadastro.setNome("c");
        cadastro.setCpf("0987222222");
        cadastro.setCep("44094018");
        cadastro.setEmail("chelsea@oei");
        cadastro.setTel("11 998-1286");
        cadastro.setSenha("123");
        cadastro.setTrabalhando(false);
        cadastro.setDescricao("Nem sei oque estou fazendo");
        service.cadastrarUsuario(cadastro);
    }


    @Test
    void logarUsuarioTest(){
        DadosLoginCandidatoDTO dadosLogin = new DadosLoginCandidatoDTO("chelsea@oei", "123");
        System.out.println(service.getAccessToken(dadosLogin));
    }
}
