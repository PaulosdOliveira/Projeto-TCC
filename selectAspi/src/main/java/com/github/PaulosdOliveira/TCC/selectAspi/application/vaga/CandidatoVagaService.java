package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
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

}
