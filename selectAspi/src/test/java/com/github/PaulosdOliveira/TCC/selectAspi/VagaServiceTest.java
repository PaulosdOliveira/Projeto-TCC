package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.vaga.VagaEmpregoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VagaServiceTest {

    @Autowired
    private VagaEmpregoService service;


    @Test
    void cadastrarVagaTest(){
        CadastroVagaDTO dados = new CadastroVagaDTO();
        dados.setCep("44094018");
        dados.setDescricao("Vaga de teste");
        dados.setTitulo("Testador de método");
        dados.setNivel(Nivel.INDEFINIDO);
        dados.setModelo(Modelo.PRESENCIAL);
        dados.setDiasEmAberto(3L);
        dados.setTipoContrato(TipoContrato.CLT);
        dados.setSalario(3000.54F);
        service.cadastrarVaga(dados);
    }


}
