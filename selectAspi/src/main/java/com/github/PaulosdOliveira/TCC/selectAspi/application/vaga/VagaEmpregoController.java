package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.ConsultaCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

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

    // Buscando candidaturas do candidato logado
    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/candidaturas")
    public List<CandidaturaCandidato> buscarCandidaturas() {
        return candidatoVagaService.buscarCandidaturas();
    }

    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/cancelar-candidatura/{idVaga}")
    public void cancelarCandidatura(@PathVariable("idVaga") Long idVaga) {
        candidatoVagaService.cancelarCandidatura(idVaga);
    }


    @GetMapping("")
    public List<CardVagaDTO> buscarVagas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String idEstado,
            @RequestParam(required = false) String idCidade,
            @RequestParam(required = false) String senioridade,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String tipo_contrato
    ) {
        return service.buscarVagas(titulo, idEstado, idCidade, senioridade, modelo, tipo_contrato);
    }

    @GetMapping("/{idVaga}")
    public ConsultaVagaDTO carregarVaga(@PathVariable("idVaga") Long id) {
        return service.carregarVaga(id);
    }

    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/alinhada")
    public List<CardVagaDTO> buscarVagasAlinhadas() {
        return service.buscarVagasAlinhadas();
    }


    @GetMapping(params = "idEmpresa")
    public List<VagaEmpresaDTO> buscarvagasEmpresa(@RequestParam UUID idEmpresa) {
        return service.buscarVagasEmpresa(idEmpresa);
    }

    @GetMapping("/candidatos/{idVaga}")
    @PreAuthorize("hasRole('empresa')")
    public List<CandidatoCadastradoDTO> buscarCandidatosVaga(@PathVariable Long idVaga) {
        return candidatoVagaService.buscarCandidatosVaga(idVaga);
    }


    @PutMapping("/{idVaga}")
    @PreAuthorize("hasRole('empresa')")
    public void editarDadosVaga(@PathVariable Long idVaga, @RequestBody @Valid CadastroVagaDTO novosDados) {
        System.out.println("Chegou aqui@@ >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        service.editarVaga(idVaga, novosDados);
    }

    @GetMapping("dados-cadastrais/{idVaga}")
    @PreAuthorize("hasRole('empresa')")
    public CadastroVagaDTO buscarDadosCadastrais(@PathVariable Long idVaga) {
        return service.buscarDadosCadastrais(idVaga);
    }


    @PutMapping("/candidato/selecionar/{idCandidato}/{idVaga}")
    public void selecionarCandidato(@PathVariable("idCandidato") Long idCandidato, @PathVariable("idVaga") Long idVaga) {
        candidatoVagaService.selecionarCandidato(idCandidato, idVaga);
    }

    @PutMapping("/candidato/dispensar/{idCandidato}/{idVaga}")
    public void dispensarCandidato(@PathVariable("idCandidato") Long idCandidato, @PathVariable("idVaga") Long idVaga) {
        CandidaturaPK idCandidatura = new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga));
        candidatoVagaService.dispensarCandidato(idCandidatura);
    }
}
