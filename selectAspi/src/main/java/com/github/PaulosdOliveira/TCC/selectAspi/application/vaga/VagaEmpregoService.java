package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaEncerradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaNaoEncontradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class VagaEmpregoService {

    private final VagaEmpregoRepository repository;
    private final EmpresaService empresaService;
    private final LocalizacaoService localizacaoService;


    public void cadastrarVaga(CadastroVagaDTO dadosCadastrais) {
        Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        var empresaLogada = empresaService.findById(id);
        String cidade = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep()).getLocalidade();
        VagaEmprego vaga = new VagaEmprego(dadosCadastrais, cidade, empresaLogada);
        repository.save(vaga);
    }


    @Transactional
    public void validarVaga(Long id){
        VagaEmprego vaga = repository.findById(id)
                .orElseThrow(VagaNaoEncontradaException::new);
        var dataAtual = LocalDateTime.now();
        var dataEncerramento = vaga.getDataHoraEncerramento();
        boolean vagaDisponivel = (dataEncerramento != null && dataAtual.isBefore(dataEncerramento) && vaga.isVagaAtiva())
                                 || dataEncerramento == null && vaga.isVagaAtiva();
        if(! vagaDisponivel) {
            vaga.setVagaAtiva(false);
            throw new VagaEncerradaException();
        }

    }


}
