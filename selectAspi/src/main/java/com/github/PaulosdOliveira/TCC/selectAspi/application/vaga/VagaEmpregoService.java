package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.LocalizacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaNaoEncontradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaEncerradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CadastroVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.CardVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.ConsultaVagaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.VagaEmprego;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Modelo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.enums.Nivel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class VagaEmpregoService {

    private final CandidatoService candidatoService;
    private final VagaEmpregoRepository repository;
    private final CandidatoVagaRepository candidatoVagaRepository;
    private final EmpresaService empresaService;
    private final LocalizacaoService localizacaoService;


    public void cadastrarVaga(CadastroVagaDTO dadosCadastrais) {
        String id = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var empresaLogada = new Empresa(id);
        var localizacao = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep());
        String estado = localizacao.getUf();
        String cidade = localizacao.getLocalidade();
        VagaEmprego vaga = new VagaEmprego(dadosCadastrais, estado, cidade, empresaLogada);
        repository.save(vaga);
    }


    @Transactional
    public List<CardVagaDTO> buscarVagas(String titulo, String estado, String cidade, String senioridade, String modelo, String tipo_contrato) {
        var filtro = candidatoService.buscarFiltroVaga();
        var vagas = repository.buscarVagas(titulo, estado, cidade, senioridade, filtro.sexo(), filtro.pcd(), modelo, tipo_contrato);
        verificarVagas(vagas);
        return convertToCard(vagas);
    }

    private void verificarVagas(List<VagaEmprego> vagas) {
        vagas.removeIf(vagaAtual -> {
            boolean deverRemover = vagaAtual.getDataHoraEncerramento().isBefore(LocalDateTime.now());
            if(deverRemover) vagaAtual.setVagaAtiva(false);
            return  deverRemover;
        });
    }

    public List<CardVagaDTO> buscarVagasAlinhadas() {
        List<String> qualificacoes = candidatoService.buscarQualificacoes();
        var vagas = repository.buscarVagasAlinhadas(qualificacoes);
        return convertToCard(vagas);
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


    public List<String> buscarEstadosCadastrados() {
        return repository.buscarEstados();
    }

    public List<String> buscarCidadesEstado(String estado) {
        return repository.buscarCidades(estado);
    }

    // TRANSFORMANDO LISTA DE VAGAS EM CARD
    private List<CardVagaDTO> convertToCard(List<VagaEmprego> vagas) {
        return vagas.stream().map(vaga -> new CardVagaDTO(
                vaga.getId(), vaga.getEmpresa().getNome(), vaga.getTitulo(),
                vaga.getCidade(), vaga.getEstado(), vaga.getSalario(),
                vaga.getModelo().name(), vaga.getTipoContrato().name(), vaga.getNivel().name(),
                vaga.getExclusivoParaPcd(), vaga.getExclusivoParaSexo(), getTempoDecorrido(vaga.getDataHoraPublicacao())
        )).toList();
    }

    // VERIFICANDO O TEMPO DECORRIDO DESDE A POSTAGEM DA VAGA
    private String getTempoDecorrido(LocalDateTime dataEnvio) {
        var dataAtual = LocalDateTime.now();
        long anos = ChronoUnit.YEARS.between(dataEnvio, dataAtual);
        if (anos > 0) return "Há " + anos + " Anos";
        long meses = ChronoUnit.MONTHS.between(dataEnvio, dataAtual);
        if (meses > 0) return "Há " + meses + " Meses";
        long dias = ChronoUnit.DAYS.between(dataEnvio, dataAtual);
        if (dias > 0) return "Há " + dias + " Dias";
        long horas = ChronoUnit.HOURS.between(dataEnvio, dataAtual);
        if (horas > 0) return "Há " + horas + " Horas";
        long minutos = ChronoUnit.MINUTES.between(dataEnvio, dataAtual);
        if (minutos > 0) return "Há " + minutos + " Minutos";
        return "Agora";
    }

    public ConsultaVagaDTO carregarVaga(Long id) {
        Long idCandidatoLogado = candidatoService.getIdCandidatoLogado();
        Candidato candidatoLogado = new Candidato(idCandidatoLogado);
        VagaEmprego vaga = new VagaEmprego(id);
        boolean jaCandidatou_se = candidatoVagaRepository.existsById(new CandidaturaPK(candidatoLogado, vaga));
        ConsultaVagaDTO vagaEncontrada = repository.carregarVaga(id).orElse(new ConsultaVagaDTO());
        vagaEncontrada.setJaCandidatou(jaCandidatou_se);
        return vagaEncontrada ;
    }
}
