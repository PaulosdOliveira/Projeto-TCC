package com.github.PaulosdOliveira.TCC.selectAspi.application.curso;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CadastroCursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoComplementar;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('candidato')")
    @PostMapping("")
    public void cadastrarCurso(@RequestBody List<CadastroCursoDTO> cursos) {
        service.cadastrarCursos(cursos, new Candidato(getIdCandidatoLogado()));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
    @GetMapping("/{idCandidato}")
    public List<CursoDTO> buscarCursosCandidato(@PathVariable Long idCandidato) {
        return service.buscarCursosCandidato(idCandidato);
    }


    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletado!"),
    })
    @DeleteMapping("/{idCurso}")
    public void deletarCurso(@PathVariable UUID idCurso) {
        service.deletarCurso(idCurso);
    }
}
