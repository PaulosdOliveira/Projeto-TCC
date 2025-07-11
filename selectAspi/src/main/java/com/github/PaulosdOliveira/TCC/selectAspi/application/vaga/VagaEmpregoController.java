package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CardVagaDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.List;


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


    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/buscar")
    public List<CardVagaDTO> buscarVagas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String senioridade,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String tipo_contrato
    ) {
        return service.buscarVagas(titulo, estado, cidade, senioridade, modelo, tipo_contrato);
    }

    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/alinhada")
    public List<CardVagaDTO> buscarVagasAlinhadas() {
        return service.buscarVagasAlinhadas();
    }

    @GetMapping("/estados")
    public List<String> buscarEstados() {
        return service.buscarEstadosCadastrados();
    }

    @GetMapping("/cidades/{uf}")
    public List<String> buscarCidades(@PathVariable String uf) {
        return service.buscarCidadesEstado(uf);
    }
}
