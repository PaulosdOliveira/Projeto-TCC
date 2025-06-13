package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoUsuarioRepository;
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

    public void cadastrarQualificacaoUsuario(QualificacaoUsuarioDTO dto, Long idCandidatoLogado) {
        qualificacaoUsuarioRepository.insert( idCandidatoLogado , dto.getIdQualificacao(), dto.getNivel().name());
    }

    public List<String> getQualificacaoByIdCandidatos(Long id) {
        return qualificacaoUsuarioRepository.getQualifcacoesById(id);
    }

}
