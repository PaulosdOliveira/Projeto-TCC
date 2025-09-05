package com.github.PaulosdOliveira.TCC.selectAspi.application.formacao;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("formacao")
public class FormacaoController {


    @Autowired
    private FormacaoService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('candidato')")
    @PostMapping("/formacao")
    public void cadastrarFormacao(@RequestBody List<@Valid Formacao> formacoes) {
        service.salvarFormacoes(formacoes, new Candidato(getIdCandidatoLogado()));
    }

    @GetMapping("/formacao/{idCandidato}")
    public List<FormacaoDTO> buscarFormacoesCandidato(@PathVariable Long idCandidato) {
        return service.buscarFormacoesCandidato(idCandidato);
    }

    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/formacao/{idFormacao}")
    public void deletarFormacao(UUID idFormacao) {
        service.deletarFormacao(idFormacao);
    }

}
