package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;
    private final LocalizacaoService localizacaoService;

    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) throws Exception {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf());
        Localizacao localizacao;
        localizacao = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep());
        if (localizacao.getLocalidade() == null) throw new CepInvalidoException();
        System.out.println(localizacao.getLocalidade());
        Candidato candidato = new Candidato(dadosCadastrais);
        candidato.setUf(localizacao.getUf());
        candidato.setLocalidade(localizacao.getLocalidade());
        repository.save(candidato);
    }

    public void salvarFotoCandidato(byte[] foto) {
        Long idCandidatoLogado = 1L; // Pegar o id no contexto de segurança
        repository.salvarFotoCandidato(idCandidatoLogado, foto);
    }

    public byte[] buscarFotoCandidato() {
        Long idCandidatoLogado = 1L; // Pegar id usuário logado
        return repository.buscarFotoCandidato(idCandidatoLogado);
    }

    public void salvarCurriculo(byte[] curriculo) {
        Long idCandidatoLogado = 1L; // Pegar o id no contexto de segurança
        repository.salvarCurriculoCandidato(idCandidatoLogado, curriculo);
    }

    public byte[] buscarCurriculoCandidato() {
        Long idCandidatoLogado = 1L; // Id da pessoa logada
        return repository.buscarCurriculoCandidato(idCandidatoLogado);
    }
}
