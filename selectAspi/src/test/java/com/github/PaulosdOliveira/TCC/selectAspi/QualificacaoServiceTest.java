package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QualificacaoServiceTest {

    @Autowired
    private QualificacaoService service;

    @Test
    void cadastrarQualificacaoTest(){
        Qualificacao qualificacao = new Qualificacao();
        qualificacao.setNome("Python");
        service.cadastrarQualificacao(qualificacao);
    }

    @Test
    void cadastrarQualificacaoUsuarioTest(){
        QualificacaoUsuarioDTO dto = new QualificacaoUsuarioDTO();
        dto.setIdQualificacao(1L);
        dto.setNivel(Nivel.BASICO);
        service.cadastrarQualificacaoUsuario(dto, 1L);
    }

}
