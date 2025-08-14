package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.UsuarioNaoEncontradoException;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.*;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacoesSalvas;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final QualificacaoService qualificacaoService;

    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) throws Exception {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf(), dadosCadastrais.getDataNascimento());
        dadosCadastrais.setSenha(encoder.encode(dadosCadastrais.getSenha()));
        Candidato candidato = new Candidato(dadosCadastrais);
        repository.save(candidato);
    }


    public Candidato findById(Long id){
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
        System.out.println("O que está acontecendo ??????  " + login);
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


    public void cadastarQualificacaoUsuario(List<QualificacaoUsuarioDTO> qualificacoes) {
        qualificacaoService.cadastrarQualificacaoUsuario(qualificacoes, getIdCandidatoLogado());
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
        List<QualificacoesSalvas> qualificacoes = qualificacaoService.buscarQualificacoesPerfil(id);
        PerfilCandidatoDTO perfil = repository.carregarPerfilCandidato(id).orElseThrow(UsuarioNaoEncontradoException::new);
        perfil.setQualificacoes(qualificacoes);
        return perfil;
    }


}
