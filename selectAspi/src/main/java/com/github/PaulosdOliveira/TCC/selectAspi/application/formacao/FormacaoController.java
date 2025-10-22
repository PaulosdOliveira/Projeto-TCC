package com.github.PaulosdOliveira.TCC.selectAspi.application.formacao;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.CadastroFormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.OptionFormacaoDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('candidato')")
    @PostMapping("")
    public void cadastrarFormacao(@RequestBody List<@Valid CadastroFormacaoDTO> formacoes) {
        service.salvarFormacoes(formacoes, new Candidato(getIdCandidatoLogado()));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "cadastrado com sucesso"),
    })
    @GetMapping("/{idCandidato}")
    public List<FormacaoDTO> buscarFormacoesCandidato(@PathVariable Long idCandidato) {
        return service.buscarFormacoesCandidato(idCandidato);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @GetMapping(params = "curso")
    public List<OptionFormacaoDTO> findCursoLikeString(@RequestParam String curso) {
        return service.distinctCursosLikeString(curso);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/{idFormacao}")
    public void deletarFormacao(@PathVariable UUID idFormacao) {
        service.deletarFormacao(idFormacao);
    }

}
