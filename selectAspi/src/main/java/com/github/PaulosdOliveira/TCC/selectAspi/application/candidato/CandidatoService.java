package com.github.PaulosdOliveira.TCC.selectAspi.application.candidato;

import com.github.PaulosdOliveira.TCC.selectAspi.application.qualificacao.QualificacaoService;
import com.github.PaulosdOliveira.TCC.selectAspi.exception.CepInvalidoException;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.*;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.CandidatoRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.ConsultaQualificacaoUsuario;
import com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao.QualificacaoUsuarioDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.CandidatoValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CandidatoService {

    private final CandidatoRepository repository;
    private final CandidatoValidator validator;
    private final LocalizacaoService localizacaoService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final QualificacaoService qualificacaoService;

    public void cadastrarUsuario(CadastroCandidatoDTO dadosCadastrais) throws Exception {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCpf(), dadosCadastrais.getDataNascimento());
        Localizacao localizacao;
        localizacao = localizacaoService.buscarLocalizacaoPorCep(dadosCadastrais.getCep());
        if (localizacao.getLocalidade() == null) throw new CepInvalidoException();
        Candidato candidato = new Candidato(dadosCadastrais, encoder.encode(dadosCadastrais.getSenha()));
        candidato.setUf(localizacao.getUf());
        candidato.setLocalidade(localizacao.getLocalidade());
        repository.save(candidato);
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
        System.out.println("O que está acontecendo ??????  " +   login);
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

    public List<Candidato> findByQualificacao(List<ConsultaQualificacaoUsuario> qualificacoes, String estado, String cidade) {
        return repository.findCandidatoByQualificacao(qualificacoes, estado, cidade);
    }

    // Buscando informações do perfil do candidato para a consulta de vagas
    public DadosFitroVaga buscarFiltroVaga() {
        return repository.buscarDadosFiltroVaga(getIdCandidatoLogado());
    }

    // Buscar qualificações do usuário logado
    public List<String> buscarQualificacoes() {
        return qualificacaoService.getQualificacaoByIdCandidato(getIdCandidatoLogado());
    }



}
