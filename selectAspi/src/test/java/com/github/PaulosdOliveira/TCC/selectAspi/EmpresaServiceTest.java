package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpresaServiceTest {

    @Autowired
    private EmpresaService service;

    @Autowired
    private EmpresaRepository repository;

    @Test
    void cadastrarEmpresaTest(){
        CadastroEmpresaDTO dados = new CadastroEmpresaDTO();
        dados.setNome("Atacadão");
        dados.setEmail("a@t");
        dados.setSenha("123");
        dados.setCnpj("100000011");
        dados.setDescricao("Testando cadastro");
        service.cadastrarEmpresa(dados);
    }

    @Test
    void getAccessTokenTest(){
        DadosLoginEmpresaDTO dados = new DadosLoginEmpresaDTO();
        dados.setLogin("a@t");
        dados.setSenha("123");
        System.out.println(service.getAccessToken(dados));
    }

    @Test
    void buscarEmpresas(){
        repository.findAll()
                .forEach(e -> System.out.println("Nome: " + e.getId()));
    }
}
