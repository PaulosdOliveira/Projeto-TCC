package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CPFDuplicadoExcepton;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.EmailDuplicadoException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidatoValidator {

    @Autowired
    private  CandidatoRepository repository;

    public void validar(String email, String cpf) {
       if(repository.existsByEmail(email)) throw new EmailDuplicadoException();
       if(repository.existsByCpf(cpf)) throw new CPFDuplicadoExcepton();
    }
}
