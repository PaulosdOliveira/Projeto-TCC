package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vaga")
public class VagaEmpregoController {

    @Autowired
    private VagaEmpregoService service;

    @Autowired
    private CandidatoVagaService candidatoVagaService;


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('empresa')")
    @PostMapping
    public void cadastrarVaga(@RequestBody @Valid CadastroVagaDTO dadosCadastrais) {
        service.cadastrarVaga(dadosCadastrais);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('candidato')")
    @PostMapping("/candidatar/{idVaga}")
    public void cadastarCandidatura(@PathVariable Long idVaga) {
     candidatoVagaService.cadastrarCandidatura(idVaga);
    }
}
