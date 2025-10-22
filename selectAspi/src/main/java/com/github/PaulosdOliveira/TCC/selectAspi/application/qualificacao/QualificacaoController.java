package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PreAuthorize("hasRole('candidato')")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("")
    public void cadastrarQualificacao(@RequestBody List<@Valid QualificacaoUsuarioDTO> qualificacoes) {
        service.cadastrarQualificacaoUsuario(qualificacoes, new Candidato(getIdCandidatoLogado()));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(params = "nome")
    public List<Qualificacao> findByNomeLike(@RequestParam String nome) {
        return service.findAll(nome);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado!"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @DeleteMapping("/{idQualificacao}")
    public void deletarQualificacaoUsuario(@PathVariable Long idQualificacao) {
        service.deletarQualificacaoSalva(idQualificacao);
    }


}
