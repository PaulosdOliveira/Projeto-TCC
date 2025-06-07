package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.CadastroCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Localizacao;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;
    private final LocalizacaoService localizacaoService;

    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf());
        try {
            Localizacao localizacao = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep());
            Candidato candidato = new Candidato(dadosCadastrais);
            candidato.setUf(localizacao.getUf());
            candidato.setLocalidade(localizacao.getLocalidade());
            repository.save(candidato);
        } catch (WebClientResponseException.BadRequest e) {
            throw new CepInvalidoException();
        }
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
