package com.github.PaulosdOliveira.TCC.selectAspi.application.formacao;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.FormacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.CadastroFormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.OptionFormacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;
import java.util.List;
import java.util.UUID;

@Service
public class FormacaoService {

    @Autowired
    private FormacaoRepository repository;

    public void salvarFormacoes(List<CadastroFormacaoDTO> formacoesDTO, Candidato candidato) {
        var formacoes = formacoesDTO.stream().map(dto -> new Formacao(dto, candidato)).toList();
        repository.saveAll(formacoes);
    }

    public List<FormacaoDTO> buscarFormacoesCandidato(Long idCandidato) {
        return repository.buscarFormacoesCandidato(idCandidato);
    }

    public void deletarFormacao(UUID idFormacao) {
        Long idCandidato = getIdCandidatoLogado();
        repository.deletarFormacao(idFormacao, 43L);
    }

    public List<OptionFormacaoDTO> distinctCursosLikeString(String curso){
        return repository.distinctCursosLikeString(curso);
    }

}
