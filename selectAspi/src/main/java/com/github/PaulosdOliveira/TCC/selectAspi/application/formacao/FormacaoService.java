package com.github.PaulosdOliveira.TCC.selectAspi.application.formacao;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.FormacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.Formacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;
import java.util.List;
import java.util.UUID;

@Service
public class FormacaoService {

    @Autowired
    private FormacaoRepository repository;

    public void salvarFormacoes(List<Formacao> formacoes) {
        Long idCandidatoLogado = getIdCandidatoLogado();
        formacoes.removeIf(f -> {
            f.setCandidato(new Candidato(idCandidatoLogado));
            return false;
        });
        repository.saveAll(formacoes);
    }

    public List<FormacaoDTO> buscarFormacoesCandidato(Long idCandidato) {
        return repository.buscarFormacoesCandidato(idCandidato);
    }

    public void deletarFormacao(UUID idFormacao) {
        Long idCandidato = getIdCandidatoLogado();
        repository.deletarFormacao(idFormacao, idCandidato);
    }

}
