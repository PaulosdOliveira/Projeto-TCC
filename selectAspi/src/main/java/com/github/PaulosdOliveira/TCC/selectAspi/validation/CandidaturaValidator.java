package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CandidaturaJaCadastradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidaturaValidator {

    @Autowired
    private CandidatoVagaRepository repository;

    public void validar(Long idCandidato, Long idVaga){
        if(repository.candidaturaExiste(idCandidato, idVaga).isPresent()) throw new CandidaturaJaCadastradaException();
    }
}
