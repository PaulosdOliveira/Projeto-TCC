package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoCadastradoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidatoVaga;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaCandidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.StatusCandidatura;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidaturaValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CandidatoVagaService {

    private final CandidatoVagaRepository repository;
    private final CandidatoService candidatoService;
    private final VagaEmpregoService vagaEmpregoService;
    private final CandidaturaValidator validator;


    public void cadastrarCandidatura(Long idVaga) {
        vagaEmpregoService.validarVaga(idVaga);
        Long idCandidato = candidatoService.getIdCandidatoLogado();
        validator.validar(idCandidato, idVaga);
        repository.save(new CandidatoVaga(idCandidato, idVaga));
    }

    @Transactional
    public void cancelarCandidatura(Long idVaga) {
        Long idCandidato = candidatoService.getIdCandidatoLogado();
        var idCandidatura = new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga));
        var candidatura = repository.findById(idCandidatura).orElseThrow();
        if (candidatura.getStatus() == StatusCandidatura.EM_ANALISE) repository.deleteById(idCandidatura);
        else throw new RuntimeException("Vaga candidatura de definida");
    }

    public Page<CandidatoCadastradoDTO> buscarCandidatosVaga(Long idVaga, StatusCandidatura status, int pageNumber) {
        return repository.buscarCandidatosVaga(idVaga, status, PageRequest.of(pageNumber, 20));
    }


    public void selecionarCandidato(Long idCandidato, Long idVaga) {
        CandidaturaPK idCandidatura = new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga));
        repository.selecionarCandidato(idCandidatura);
    }

    public void dispensarCandidato(CandidaturaPK idCandidatura) {
        repository.dispensarCandidato(idCandidatura);
    }


    // Buscando candidaturas do candidato logado
    public List<CandidaturaCandidato> buscarCandidaturas() {
        return repository.CandidaturasCandidato(candidatoService.getIdCandidatoLogado());
    }

}
