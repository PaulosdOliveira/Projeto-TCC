package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoUsuarioRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ChaveCompostaQualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QualificacaoService {

    private final QualificacaoRepository qualificacaoRepository;
    private final QualificacaoUsuarioRepository qualificacaoUsuarioRepository;


    public void cadastrarQualificacao(Qualificacao qualificacao) {
        qualificacaoRepository.save(qualificacao);
    }

    public void cadastrarQualificacaoUsuario(List<QualificacaoUsuarioDTO> dto, Long idCandidatoLogado) {
        var candidato = new Candidato(idCandidatoLogado);
        dto.forEach(item -> {
            var qualificacao = new Qualificacao(item.getIdQualificacao());
            var idQualificacaoUsuario = new ChaveCompostaQualificacao(candidato, qualificacao);
            boolean jaCadastrado = qualificacaoUsuarioRepository.existsById(idQualificacaoUsuario);
            if (!jaCadastrado) {
               qualificacaoUsuarioRepository.insert(idCandidatoLogado, item.getIdQualificacao(), item.getNivel().name());
            }
        });
    }

    public List<String> getQualificacaoByIdCandidato(Long id) {
        return qualificacaoUsuarioRepository.getQualifcacoesById(id);
    }


    public List<Qualificacao> findAll() {
        return qualificacaoRepository.buscarTudo();
    }

}
