package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaNaoEncontradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaEncerradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class VagaEmpregoService {

    private final CandidatoService candidatoService;
    private final VagaEmpregoRepository repository;
    private final EmpresaService empresaService;
    private final LocalizacaoService localizacaoService;


    public void cadastrarVaga(CadastroVagaDTO dadosCadastrais) {
        String id = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var empresaLogada = empresaService.findById(UUID.fromString(id));
        var localizacao = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep());
        String estado = localizacao.getUf();
        String cidade = localizacao.getLocalidade();
        VagaEmprego vaga = new VagaEmprego(dadosCadastrais, estado, cidade, empresaLogada);
        repository.save(vaga);
    }


    public List<ConsultaVagaDTO> buscarVagas(String titulo, String estado, String cidade, Nivel senioridade, Modelo modelo) {
        var filtro = candidatoService.buscarFiltroVaga();
        return repository.buscarVagas(titulo, estado, cidade, senioridade, filtro.sexo(), filtro.pcd(), modelo);
    }


    public List<ConsultaVagaDTO> buscarVagasAlinhadas() {
        List<String> qualificacoes = candidatoService.buscarQualificacoes();
        return repository.buscarVagasAlinhadas(qualificacoes);
    }


    @Transactional
    public void validarVaga(Long id) {
        VagaEmprego vaga = repository.findById(id)
                .orElseThrow(VagaNaoEncontradaException::new);
        var dataAtual = LocalDateTime.now();
        var dataEncerramento = vaga.getDataHoraEncerramento();
        boolean vagaDisponivel = (dataEncerramento != null && dataAtual.isBefore(dataEncerramento) && vaga.isVagaAtiva())
                || dataEncerramento == null && vaga.isVagaAtiva();
        if (!vagaDisponivel) {
            vaga.setVagaAtiva(false);
            throw new VagaEncerradaException();
        }

    }


}
