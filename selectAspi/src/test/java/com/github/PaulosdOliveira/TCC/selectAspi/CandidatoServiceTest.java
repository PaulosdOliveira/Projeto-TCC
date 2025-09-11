package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.FormacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosLoginCandidatoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    private CandidatoService service;

    @Autowired
    private CandidatoRepository repository;

    @Autowired
    private FormacaoRepository formacaoRepository;

    @Test
    void cadastrarCandidatoTest() throws Exception {
        CadastroCandidatoDTO cadastro = new CadastroCandidatoDTO();
        cadastro.setNome("c");
        cadastro.setCpf("0987222222");
        cadastro.setEmail("chelsea@oei");
        cadastro.setTel("11 998-1286");
        cadastro.setSenha("123");
        cadastro.setTrabalhando(false);
        cadastro.setDescricao("Nem sei oque estou fazendo");
        service.cadastrarUsuario(cadastro);
    }


    @Test
    void logarUsuarioTest() {
        DadosLoginCandidatoDTO dadosLogin = new DadosLoginCandidatoDTO("chelsea@oei", "123");
        System.out.println(service.getCandidatoAccessToken(dadosLogin));
    }


    @Test
    void test() {
        repository.findAll().forEach(System.out::println);
    }

    @Test
    void testar() {
        formacaoRepository.deletarFormacao(UUID.fromString("9df5b745-b02c-4bd7-8e70-e767f8aa823b"), 44L);
    }

}
