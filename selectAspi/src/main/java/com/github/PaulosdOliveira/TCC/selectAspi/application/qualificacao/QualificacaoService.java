package com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao;


import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.QualificacaoUsuarioRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getIdCandidatoLogado;

@RequiredArgsConstructor
@Service
public class QualificacaoService {

    private final QualificacaoRepository qualificacaoRepository;
    private final QualificacaoUsuarioRepository qualificacaoUsuarioRepository;


    public void cadastrarQualificacao(Qualificacao qualificacao) {
        qualificacaoRepository.save(qualificacao);
    }

    public void cadastrarQualificacaoUsuario(List<QualificacaoUsuarioDTO> dtoList, Candidato candidato) {
        List<QualificacaoUsuario> qualificacoes = dtoList.stream().map(item ->
                        new QualificacaoUsuario(new ChaveCompostaQualificacao(candidato, new Qualificacao(item.getIdQualificacao())), item.getNivel()))
                .toList();
        System.out.println(dtoList);
        qualificacaoUsuarioRepository.saveAll(qualificacoes);
    }

    public List<String> getQualificacaoByIdCandidato(Long id) {
        return qualificacaoUsuarioRepository.getQualifcacoesById(id);
    }


    public List<Qualificacao> findAll(String nome) {
        return qualificacaoRepository.findByNomeLike(nome);
    }


    public List<QualificacoesSalvas> buscarQualificacoesPerfil(Long idCandidato) {
        return qualificacaoUsuarioRepository.buscarQualificacoesPerfil(idCandidato);
    }

    public void deletarQualificacaoSalva(Long idQualificacao) {
        var idCandidato = getIdCandidatoLogado();
        qualificacaoUsuarioRepository.deletarQualificacaoSalva(idQualificacao, idCandidato);
    }
}
