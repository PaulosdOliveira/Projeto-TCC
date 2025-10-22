package com.github.PaulosdOliveira.TCC.selectAspi.application.experiencia;


import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.CadastroExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.Experiencia;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
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
@RequestMapping("experiencia")
public class ExperienciaController {

    @Autowired
    private ExperienciaService service;


    @ApiResponses({
          @ApiResponse(responseCode = "201", description = "cadastrado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @ResponseStatus(HttpStatus.CREATED)
      @PreAuthorize("hasRole('candidato')")
    @PostMapping()
    public void cadastrarExperiencia(@RequestBody List<CadastroExperienciaDTO> experiencias) {
        service.cadastrarExperiencia(experiencias, new Candidato(getIdCandidatoLogado()));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
    @GetMapping("/{idCandidato}")
    public List<ExperienciaDTO> buscarExperienciasCandidato(@PathVariable Long idCandidato) {
        return service.buscarExperienciasCandidato(idCandidato);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado!"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/{id}")
    public void deletarExperienciasCandidato(@PathVariable UUID id) {
        service.deletarExperiencia(id);
    }
}
