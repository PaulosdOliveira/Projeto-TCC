package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.application.curso.CursoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.experiencia.ExperienciaService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.formacao.FormacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.UsuarioNaoEncontradoException;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.*;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.curso.CursoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia.ExperienciaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.formacao.FormacaoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacoesSalvas;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.EnderecoBaseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;
    private final EnderecoBaseValidator enderecoValidator;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final QualificacaoService qualificacaoService;
    private final FormacaoService formacaoService;
    private final ExperienciaService experienciaService;
    private final CursoService cursoService;


    @Transactional
    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) throws Exception {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf(), dadosCadastrais.getDataNascimento());
        enderecoValidator.validar(dadosCadastrais.getIdEstado(), dadosCadastrais.getIdCidade());
        dadosCadastrais.setSenha(encoder.encode(dadosCadastrais.getSenha()));
        Candidato candidato = new Candidato(dadosCadastrais);
        repository.save(candidato);
        formacaoService.salvarFormacoes(dadosCadastrais.getFormacoes(), candidato);
        experienciaService.cadastrarExperiencia(dadosCadastrais.getExperiencias(), candidato);
        cursoService.cadastrarCursos(dadosCadastrais.getCursos(), candidato);
        qualificacaoService.cadastrarQualificacaoUsuario(dadosCadastrais.getQualificacoes(), candidato);
    }


    public Candidato findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void salvarFotoCandidato(byte[] foto) {
        repository.salvarFotoCandidato(getIdCandidatoLogado(), foto);
    }

    public byte[] buscarFotoCandidato(Long idCandidato) {
        return repository.buscarFotoCandidato(idCandidato);
    }

    public void salvarCurriculo(byte[] curriculo) {
        repository.salvarCurriculoCandidato(getIdCandidatoLogado(), curriculo);
    }

    public byte[] buscarCurriculoCandidato(Long idCandidato) {
        return repository.buscarCurriculoCandidato(idCandidato);
    }

    public String getCandidatoAccessToken(DadosLoginCandidatoDTO dadosLogin) {
        System.out.println(dadosLogin.getLogin() + "Email informado");
        LoginCandidatoDTO candidatoEnontrado = buscarPorEmailOuCpf(dadosLogin.getLogin());
        if (candidatoEnontrado != null) {
            if (encoder.matches(dadosLogin.getSenha(), candidatoEnontrado.getSenha()))
                return jwtService.getAccessToken(candidatoEnontrado.getId().toString(),
                        candidatoEnontrado.getEmail(), candidatoEnontrado.getNome(), "candidato");
        }
        throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
    }

    public LoginCandidatoDTO buscarPorEmailOuCpf(String login) {
        return repository.buscarCandidatoLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Long getIdCandidatoLogado() {
        return Long.parseLong(
                SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()
        );
    }

    public void logarCandidato(String email) {
        LoginCandidatoDTO loginDTO = buscarPorEmailOuCpf(email);
        UserDetails userDetails = User.withUsername(loginDTO.getEmail())
                .authorities("candidato")
                .password(loginDTO.getSenha())
                .build();
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, loginDTO.getId(), userDetails.getAuthorities()));
    }

    public List<Candidato> findByQualificacao(DadosConsultaCandidatoDTO dadosConsulta) {
        return repository.findCandidatoByQualificacao(dadosConsulta.getQualificacoes(), dadosConsulta.getIdEstado(), dadosConsulta.getIdCidade(),
                dadosConsulta.getSexo(), dadosConsulta.isPcd(), dadosConsulta.getTrabalhando());
    }

    // Buscando informações do perfil do candidato para a consulta de vagas
    public DadosFitroVaga buscarFiltroVaga() {
        return repository.buscarDadosFiltroVaga(getIdCandidatoLogado());
    }

    // Buscar qualificações do usuário logado
    public List<String> buscarQualificacoes() {
        return qualificacaoService.getQualificacaoByIdCandidato(getIdCandidatoLogado());
    }


    public PerfilCandidatoDTO carregarPerfil(Long id) {
        List<ExperienciaDTO> experiencias = experienciaService.buscarExperienciasCandidato(id);
        List<CursoDTO> cursos = cursoService.buscarCursosCandidato(id);
        List <FormacaoDTO > formacoes = formacaoService.buscarFormacoesCandidato(id);
        List<QualificacoesSalvas> qualificacoes = qualificacaoService.buscarQualificacoesPerfil(id);
        PerfilCandidatoDTO perfil = repository.carregarPerfilCandidato(id).orElseThrow(UsuarioNaoEncontradoException::new);
        perfil.completarPerfil(qualificacoes, experiencias, cursos, formacoes);
        return perfil;
    }


}
