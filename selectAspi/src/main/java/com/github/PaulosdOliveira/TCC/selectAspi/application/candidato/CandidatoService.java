package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.nfra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;

    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf());
        repository.save(new Candidato(dadosCadastrais));
    }

    public void salvarFotoCandidato(byte[] foto) {
        Long idCandidatoLogado = 1L; // Pegar o id no contexto de segurança
        repository.salvarFotoCandidato(idCandidatoLogado, foto);
    }
    
    public void salvarCurriculo(byte[] curriculo) {
        Long idCandidatoLogado = 1L; // Pegar o id no contexto de segurança
        repository.salvarCurriculoCandidato(idCandidatoLogado, curriculo);
    }
}
