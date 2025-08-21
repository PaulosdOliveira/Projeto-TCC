package com.github.PaulosdOliveira.TCC.selectAspi.application.vaga;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoVagaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.VagaEmpregoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaNaoEncontradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.VagaEncerradaException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Sexo;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Cidade;
import com.github.PaulosdOliveira.TCC.selectAspi.model.localizacao.Estado;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.*;
import com.github.PaulosdOliveira.TCC.selectAspi.model.vaga.candidato.CandidaturaPK;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.github.PaulosdOliveira.TCC.selectAspi.application.UtilsService.getTempoDecorrido;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class VagaEmpregoService {

    private final CandidatoService candidatoService;
    private final VagaEmpregoRepository repository;
    private final CandidatoVagaRepository candidatoVagaRepository;

    public void cadastrarVaga(CadastroVagaDTO dadosCadastrais) {
        String id = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        var empresaLogada = new Empresa(id);
        VagaEmprego vaga = new VagaEmprego(dadosCadastrais, empresaLogada);
        repository.save(vaga);
    }

    public VagaEmprego findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void editarVaga(Long idVaga, CadastroVagaDTO dados) {
        VagaEmprego vaga = repository.findById(idVaga).orElseThrow();
        vaga.setTitulo(dados.getTitulo());
        vaga.setDescricao(dados.getDescricao());
        vaga.setDiferenciais(dados.getDiferenciais());
        vaga.setExclusivoParaSexo(dados.getExclusivoParaSexo());
        vaga.setExclusivoParaPcd(dados.getExclusivoParaPcd());
        vaga.setRequisitos(dados.getRequisitos());
        vaga.setPrincipais_atividades(dados.getPrincipais_atividades());
        vaga.setHorario(dados.getHorario());
        vaga.setLocal_de_trabalho(dados.getLocal_de_trabalho());
        vaga.setNivel(dados.getNivel());
        vaga.setModelo(dados.getModelo());
        vaga.setCidade(new Cidade(dados.getIdCidade()));
        vaga.setEstado(new Estado(dados.getIdEstado()));
        vaga.setSalario(dados.getSalario());
        vaga.setTipoContrato(dados.getTipoContrato());
        vaga.setMensagemConvocacao(dados.getMensagemConvocacao());
        vaga.setMensagemDispensa(dados.getMensagemDispensa());
        if (dados.getDiasEmAberto() > 0)
            vaga.setDataHoraEncerramento(vaga.getDataHoraPublicacao().plusDays(dados.getDiasEmAberto()));

    }


    @Transactional
    public List<CardVagaDTO> buscarVagas(String titulo, String idEstado, String idCidade, String senioridade, String modelo, String tipo_contrato) {
        var filtro = candidatoService.buscarFiltroVaga();
        var vagas = repository.buscarVagas(titulo, idEstado, idCidade, senioridade, modelo, tipo_contrato, filtro.sexo(), filtro.pcd());
        verificarVagas(vagas);
        return convertToCard(vagas);
    }

    private void verificarVagas(List<VagaEmprego> vagas) {
        vagas.removeIf(vagaAtual -> {
            if (vagaAtual.getDataHoraEncerramento() == null) return false;
            boolean deverRemover = vagaAtual.getDataHoraEncerramento().isBefore(LocalDateTime.now());
            if (deverRemover) vagaAtual.setVagaAtiva(false);
            return deverRemover;
        });
    }

    public List<CardVagaDTO> buscarVagasAlinhadas() {
        var filtro = candidatoService.buscarFiltroVaga();
        List<String> qualificacoes = candidatoService.buscarQualificacoes();
        var vagas = repository.buscarVagasAlinhadas(qualificacoes, filtro.sexo(), filtro.pcd());
        return convertToCard(vagas);
    }

    @Transactional
    public void validarVaga(Long id) {
        VagaEmprego vaga = repository.findById(id)
                .orElseThrow(VagaNaoEncontradaException::new);
        if (candidatoNaoEnquadra(vaga.getExclusivoParaPcd(), vaga.getExclusivoParaSexo()))
            throw new RuntimeException("Você não se enquadra nesse vaga");
        var dataAtual = LocalDateTime.now();
        var dataEncerramento = vaga.getDataHoraEncerramento();
        boolean vagaDisponivel = (dataEncerramento != null && dataAtual.isBefore(dataEncerramento) && vaga.isVagaAtiva())
                                 || dataEncerramento == null && vaga.isVagaAtiva();
        if (!vagaDisponivel) {
            vaga.setVagaAtiva(false);
            throw new VagaEncerradaException();
        }
    }

    private boolean candidatoNaoEnquadra(boolean vagaExclusivaParaPcd, Sexo exclusividadeDeSexo) {
        var dadosCandidatoLogado = candidatoService.buscarFiltroVaga();
        boolean isSexoRestrito = !(exclusividadeDeSexo.equals(Sexo.TODOS) || dadosCandidatoLogado.sexo().equals(exclusividadeDeSexo));
        boolean naoSeEnquadra = vagaExclusivaParaPcd != dadosCandidatoLogado.pcd();
        System.out.println("Resultado::::  " + (isSexoRestrito || naoSeEnquadra));
        return isSexoRestrito || naoSeEnquadra;
    }

    // TRANSFORMANDO LISTA DE VAGAS EM CARD
    private List<CardVagaDTO> convertToCard(List<VagaEmprego> vagas) {
        return vagas.stream().map(vaga -> new CardVagaDTO(
                vaga.getId(), vaga.getEmpresa().getNome(), vaga.getTitulo(),
                vaga.getCidade().getNome(), vaga.getEstado().getSigla(), vaga.getSalario(),
                vaga.getModelo().name(), vaga.getTipoContrato().name(), vaga.getNivel().name(),
                vaga.getExclusivoParaPcd(), vaga.getExclusivoParaSexo(), getTempoDecorrido(vaga.getDataHoraPublicacao())
        )).toList();
    }

    public ConsultaVagaDTO carregarVaga(Long id) {
        ConsultaVagaDTO vagaEncontrada = repository.carregarVaga(id).orElseThrow(VagaNaoEncontradaException::new);
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().contains("empresa"))
            return vagaEncontrada;
        Long idCandidatoLogado = candidatoService.getIdCandidatoLogado();
        Candidato candidatoLogado = new Candidato(idCandidatoLogado);
        VagaEmprego vaga = new VagaEmprego(id);
        boolean jaCandidatou_se = candidatoVagaRepository.existsById(new CandidaturaPK(candidatoLogado, vaga));
        vagaEncontrada.setJaCandidatou(jaCandidatou_se);
        return vagaEncontrada;
    }


    public CadastroVagaDTO buscarDadosCadastrais(Long idVaga) {
        return repository.buscarDadosCadastrais(idVaga).orElseThrow();
    }

    public List<VagaEmpresaDTO> buscarVagasEmpresa(UUID idEmpresa) {
        return repository.buscarVagasEmpresa(idEmpresa);
    }


}
