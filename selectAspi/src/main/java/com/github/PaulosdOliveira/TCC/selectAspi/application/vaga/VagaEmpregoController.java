package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import org.springframework.data.domain.Page;
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
    public PageCardVaga buscarVagas(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String idEstado,
            @RequestParam(required = false) String idCidade,
            @RequestParam(required = false) String senioridade,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String tipo_contrato,
            @RequestParam(required = true) int pageNumber
    ) {
        return service.buscarVagas(titulo, idEstado, idCidade, senioridade, modelo, tipo_contrato, pageNumber);
    }

    @GetMapping("/{idVaga}")
    public ConsultaVagaDTO carregarVaga(@PathVariable("idVaga") Long id) {
        return service.carregarVaga(id);
    }

    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/alinhada")
    public PageCardVaga buscarVagasAlinhadas() {
        return service.buscarVagasAlinhadas();
    }

    // BUSCANDO VAGAS DE UMA EMPRESA PELO ID
    @GetMapping(params = {"idEmpresa", "pageNumber"})
    public Page<VagaEmpresaDTO> buscarvagasEmpresa(@RequestParam UUID idEmpresa, @RequestParam int pageNumber) {
        return service.buscarVagasEmpresa(idEmpresa, pageNumber);
    }

    @GetMapping("/candidatos/{idVaga}/{status}/{pageNumber}")
    @PreAuthorize("hasRole('empresa')")
    public Page<CandidatoCadastradoDTO> buscarCandidatosVaga(@PathVariable Long idVaga, @PathVariable StatusCandidatura status, @PathVariable int pageNumber) {
        return candidatoVagaService.buscarCandidatosVaga(idVaga, status, pageNumber);
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
