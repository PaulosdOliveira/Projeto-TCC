package com.github.PaulosdOliveira.TCC.selectAspi.application.formacao;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.CadastroFormacaoDTO;
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
    @PostMapping("")
    public void cadastrarFormacao(@RequestBody List<@Valid CadastroFormacaoDTO> formacoes) {
        service.salvarFormacoes(formacoes, new Candidato(getIdCandidatoLogado()));
    }

    @GetMapping("/{idCandidato}")
    public List<FormacaoDTO> buscarFormacoesCandidato(@PathVariable Long idCandidato) {
        return service.buscarFormacoesCandidato(idCandidato);
    }

    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/{idFormacao}")
    public void deletarFormacao(@PathVariable UUID idFormacao) {
        service.deletarFormacao(idFormacao);
    }

}
