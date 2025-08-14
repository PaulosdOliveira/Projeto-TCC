package com.github.PaulosdOliveira.TCC.selectAspi;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Nivel;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QualificacaoServiceTest {

    @Autowired
    private QualificacaoService service;

    @Autowired
    private QualificacaoRepository repository;

    @Test
    void cadastrarQualificacaoTest() {
        List<Qualificacao> lista = List.of(new Qualificacao("C#"),
                new Qualificacao("NextJS"), new Qualificacao("Power Point"),
                new Qualificacao("Swift"), new Qualificacao("Kotlin"),
                new Qualificacao("C++"), new Qualificacao("Spring boot"),
                new Qualificacao(".Net"), new Qualificacao("TypeScript"),
                new Qualificacao("HTML"), new Qualificacao("CSS"));

        repository.saveAll(lista);
    }

    @Test
    void cadastrarQualificacaoUsuarioTest() {
        QualificacaoUsuarioDTO dto = new QualificacaoUsuarioDTO();
        dto.setIdQualificacao(1L);
        dto.setNivel(Nivel.BASICO);
        //service.cadastrarQualificacaoUsuario(dto, 1L);
    }

    @Test
    void buscarQualificacoesDoPerfil(){
        service.buscarQualificacoesPerfil(2L).forEach(System.out::println);
    }

}
