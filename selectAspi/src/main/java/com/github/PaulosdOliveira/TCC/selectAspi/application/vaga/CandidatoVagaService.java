package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidaturaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

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
        repository.cadastarCandidatura(idCandidato, idVaga, LocalDateTime.now());
    }

    public void cancelarCandidatura(Long idVaga){
        Long idCandidato = candidatoService.getIdCandidatoLogado();
        repository.deleteById(new CandidaturaPK(new Candidato(idCandidato), new VagaEmprego(idVaga)));

    }

}
