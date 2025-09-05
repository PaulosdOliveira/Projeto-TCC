package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;
import java.util.List;

@RequestMapping("qualificacao")
@RestController
public class QualificacaoController {

    @Autowired
    private QualificacaoService service;


    @PreAuthorize("hasRole('candidato')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public void cadastrarQualificacao(@RequestBody List<@Valid QualificacaoUsuarioDTO> qualificacoes) {
        service.cadastrarQualificacaoUsuario(qualificacoes, new Candidato(getIdCandidatoLogado()));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Qualificacao> findAll(){
        return service.findAll();
    }




}
