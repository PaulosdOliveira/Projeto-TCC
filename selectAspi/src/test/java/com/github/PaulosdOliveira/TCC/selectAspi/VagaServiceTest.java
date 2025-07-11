package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.vaga.VagaEmpregoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.MensagemRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem.Mensagem;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.TipoContrato;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class VagaServiceTest {

    @Autowired
    private VagaEmpregoService service;

    @Autowired
    private MensagemRepository mensagemRepository;


    @Test
    void cadastrarVagaTest() {
        /*CadastroVagaDTO dados = new CadastroVagaDTO();
        dados.setCep("01021-000");
        dados.setDescricao("Salario a combinar  ");
        dados.setTitulo("Testando o cadastro de salário a combinar");
        dados.setNivel(Nivel.INDEFINIDO);
        dados.setModelo(Modelo.REMOTO);
        dados.setDiasEmAberto(3L);
        dados.setTipoContrato(TipoContrato.PJ);
        dados.setSalario(0F);
        service.cadastrarVaga(dados);*/

    }




}
