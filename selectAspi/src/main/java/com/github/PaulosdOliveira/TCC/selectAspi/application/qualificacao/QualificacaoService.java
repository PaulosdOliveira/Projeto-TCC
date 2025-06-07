package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;

import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoUsuarioRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.Qualificacao;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QualificacaoService {

    private final QualificacaoRepository qualificacaoRepository;
    private final QualificacaoUsuarioRepository qualificacaoUsuarioRepository;


    public void cadastrarQualificacao(Qualificacao qualificacao) {
        qualificacaoRepository.save(qualificacao);
    }

    public void cadastrarQualificacaoUsuario(QualificacaoUsuarioDTO dto) {
        qualificacaoUsuarioRepository.insert(dto.getIdCandidato(), dto.getIdQualificacao(), dto.getNivel());
    }
}
