package com.github.PaulosdOliveira.TCC.selectAspi.application.curso;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CadastroCursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoComplementar;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PreAuthorize("hasRole('candidato')")
    @PostMapping("/curso")
    public void cadastrarCurso(@RequestBody List<CadastroCursoDTO> cursos) {
        service.cadastrarCursos(cursos, new Candidato(getIdCandidatoLogado()));
    }

    @GetMapping("/curso/{idCandidato}")
    public List<CursoDTO> buscarCursosCandidato(@PathVariable Long idCandidato) {
        return service.buscarCursosCandidato(idCandidato);
    }

    @DeleteMapping("/curso/{idCurso}")
    public void deletarCurso(@PathVariable UUID idCurso) {
        service.deletarCurso(idCurso);
    }
}
