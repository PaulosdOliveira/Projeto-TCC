package com.github.PaulosdOliveira.TCC.selectAspi.validation;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CPFDuplicadoExcepton;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.EmailDuplicadoException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.IdadeBaixaException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CandidatoValidator {

    @Autowired
    private CandidatoRepository repository;

    public void validar(String email, String cpf, LocalDate dataNascimento) {
        if (repository.existsByEmail(email)) throw new EmailDuplicadoException();
        if (repository.existsByCpf(cpf)) throw new CPFDuplicadoExcepton();
        validarIdade(dataNascimento);
    }

    // VALIDANDO IDADE DO USUÁRIO
    private void validarIdade(LocalDate dataNascimento) {
        var dataAtual = LocalDate.now();
        var diaDoAnoAtual = dataAtual.getDayOfYear();
        var diaAnoNascimento = dataNascimento.getDayOfYear();
        int idade = dataAtual.getYear() - dataNascimento.getYear();
        if (diaAnoNascimento < diaDoAnoAtual) idade -= 1;
        if (idade < 16) throw new IdadeBaixaException();
    }
}
