package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.DadosLoginCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    private CandidatoService service;


    @Autowired
    private CandidatoRepository repository;

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
    void logarUsuarioTest() {
        DadosLoginCandidatoDTO dadosLogin = new DadosLoginCandidatoDTO("chelsea@oei", "123");
        System.out.println(service.getCandidatoAccessToken(dadosLogin));
    }

    @Test
    void buscarCandidatosPorQualificacaoTest() {
        List<ConsultaQualificacaoUsuario> qualificacoes = new ArrayList<>();
        qualificacoes.add(new ConsultaQualificacaoUsuario(1L, Nivel.BASICO));
        service.findByQualificacao(qualificacoes, null, null).forEach(System.out::println);
    }

    @Test
    void test(){
        repository.findAll().forEach(System.out::println);
    }

    @Test
    void buscarEstadosTest(){
        System.out.println(repository.buscarEstados());
    }

    @Test
    void buscarCidadesTest(){
        System.out.println(repository.buscarCidades(""));
    }
}
