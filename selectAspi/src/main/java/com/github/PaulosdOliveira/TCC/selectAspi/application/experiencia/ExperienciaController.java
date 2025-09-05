package com.github.PaulosdOliveira.TCC.selectAspi.application.experiencia;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.CadastroExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("experiencia")
public class ExperienciaController {

    @Autowired
    private ExperienciaService service;

    @PreAuthorize("hasRole('candidato')")
    @PostMapping("/experiencia")
    public void cadastrarExperiencia(@RequestBody List<CadastroExperienciaDTO> experiencias) {
        service.cadastrarExperiencia(experiencias, new Candidato(getIdCandidatoLogado()));
    }

    @GetMapping("/experiencia/{idCandidato}")
    public List<ExperienciaDTO> buscarExperienciasCandidato(@PathVariable Long idCandidato) {
        return service.buscarExperienciasCandidato(idCandidato);
    }

    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/experiencia/{id}")
    public void deletarExperienciasCandidato(@PathVariable UUID id) {
        service.deletarExperiencia(id);
    }
}
