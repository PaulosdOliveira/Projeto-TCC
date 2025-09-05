package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CidadeInvalidaException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EnderecoBaseValidator {

    private final CidadeRepository cidadeRepository;

    public void validar(int idEstado, int idCidade) {
        cidadeRepository.validarCidade(idCidade, idEstado).orElseThrow(CidadeInvalidaException::new);
    }
}
