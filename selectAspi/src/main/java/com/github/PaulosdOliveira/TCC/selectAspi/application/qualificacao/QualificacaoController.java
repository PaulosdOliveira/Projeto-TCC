package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("qualificacao")
@RestController
public class QualificacaoController {

    @Autowired
    private QualificacaoService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Qualificacao> findAll(){
        return service.findAll();
    }

}
