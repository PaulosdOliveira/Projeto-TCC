package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.DadosConsultaVagaDTO;
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
    @PostMapping("/buscar")
    public List<ConsultaVagaDTO> buscarVagas(@RequestBody DadosConsultaVagaDTO dadosConsulta) {
        return service.buscarVagas(dadosConsulta.getTitulo(), dadosConsulta.getEstado(),
                dadosConsulta.getCidade(), dadosConsulta.getSenioridade(), dadosConsulta.getModelo());
    }

    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/alinhada")
    public List<ConsultaVagaDTO> buscarVagasAlinhadas() {
       return service.buscarVagasAlinhadas();
    }
}
