package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('empresa')")
    @PostMapping
    public void cadastrarVaga(@RequestBody @Valid CadastroVagaDTO dadosCadastrais) {
        service.cadastrarVaga(dadosCadastrais);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "candidatura realizada com sucesso"),
            @ApiResponse(responseCode = "209", description = "candidatura já realizada"),
            @ApiResponse(responseCode = "403", description = "Usuário não se adequa"),
            @ApiResponse(responseCode = "404", description = "Vaga não encontrada"),
            @ApiResponse(responseCode = "410", description = "Vaga encerrada!")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('candidato')")
    @PostMapping("/candidatar/{idVaga}")
    public void cadastarCandidatura(@PathVariable Long idVaga) {
        candidatoVagaService.cadastrarCandidatura(idVaga);
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    // Buscando candidaturas do candidato logado
    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/candidaturas")
    public List<CandidaturaCandidato> buscarCandidaturas() {
        return candidatoVagaService.buscarCandidaturas();
    }


    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "candidatura cancelada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('candidato')")
    @DeleteMapping("/cancelar-candidatura/{idVaga}")
    public void cancelarCandidatura(@PathVariable("idVaga") Long idVaga) {
        candidatoVagaService.cancelarCandidatura(idVaga);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
    })
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

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Vaga não encontrada"),
    })
    @GetMapping("/{idVaga}")
    public ConsultaVagaDTO carregarVaga(@PathVariable("idVaga") Long id) {
        return service.carregarVaga(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('candidato')")
    @GetMapping("/alinhada")
    public PageCardVaga buscarVagasAlinhadas() {
        return service.buscarVagasAlinhadas();
    }

    // BUSCANDO VAGAS DE UMA EMPRESA PELO ID DA MESMA
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "candidatura realizada com sucesso"),
    })
    @GetMapping(params = {"idEmpresa", "pageNumber"})
    public Page<VagaEmpresaDTO> buscarvagasEmpresa(@RequestParam UUID idEmpresa, @RequestParam int pageNumber) {
        return service.buscarVagasEmpresa(idEmpresa, pageNumber);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @GetMapping("/candidatos/{idVaga}/{status}/{pageNumber}")
    @PreAuthorize("hasRole('empresa')")
    public Page<CandidatoCadastradoDTO> buscarCandidatosVaga(@PathVariable Long idVaga, @PathVariable StatusCandidatura status, @PathVariable int pageNumber) {
        return candidatoVagaService.buscarCandidatosVaga(idVaga, status, pageNumber);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Dados mal preenchidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PutMapping("/{idVaga}")
    @PreAuthorize("hasRole('empresa')")
    public void editarDadosVaga(@PathVariable Long idVaga, @RequestBody @Valid CadastroVagaDTO novosDados) {
        service.editarVaga(idVaga, novosDados);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "candidatura realizada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @GetMapping("dados-cadastrais/{idVaga}")
    @PreAuthorize("hasRole('empresa')")
    public CadastroVagaDTO buscarDadosCadastrais(@PathVariable Long idVaga) {
        return service.buscarDadosCadastrais(idVaga);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('empresa')")
    @PutMapping("/candidato/selecionar/{idCandidato}/{idVaga}")
    public void selecionarCandidato(@PathVariable("idCandidato") Long idCandidato, @PathVariable("idVaga") Long idVaga) {
        candidatoVagaService.selecionarCandidato(idCandidato, idVaga);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "403", description = "Acesso negado"),
    })
    @PreAuthorize("hasRole('empresa')")
    @PutMapping("/candidato/dispensar/{idCandidato}/{idVaga}")
    public void dispensarCandidato(@PathVariable("idCandidato") Long idCandidato, @PathVariable("idVaga") Long idVaga) {
        CandidaturaPK idCandidatura = new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga));
        candidatoVagaService.dispensarCandidato(idCandidatura);
    }
}
