package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CnpjDuplicadoException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.EmailDuplicadoException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaValidator {

    @Autowired
    private EmpresaRepository repository;

    public void validar(String email, String cnpj){
     if(repository.existsByEmail(email)) throw new EmailDuplicadoException();
     if(repository.existsByCnpj(cnpj)) throw new CnpjDuplicadoException();
    }
}
