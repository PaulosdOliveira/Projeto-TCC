package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import com.github.PaulosdOliveira.TCC.selectAspi.model.AuthSocket;
import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.LoginCandidatoDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.EmpresaValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository repository;
    private final PasswordEncoder encoder;
    private final EmpresaValidator validator;
    private final JwtService jwtService;


    public void cadastrarEmpresa(CadastroEmpresaDTO dadosCadastrais) {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCnpj(), dadosCadastrais.getNome());
        dadosCadastrais.setSenha(encoder.encode(dadosCadastrais.getSenha()));
        var empresa = new Empresa(dadosCadastrais);
        repository.save(empresa);
    }


    public byte[] buscarFotoEmpresa(UUID id) {
        return repository.buscarFotoPorId(id);
    }

    public byte[] buscarCapaEmpresa(UUID id) {
        return repository.buscarCapaPorId(id);
    }


    public String getAccessToken(DadosLoginEmpresaDTO dadosLogin) {
        LoginEmpresaDTO loginDTO = buscarPorEmailOuNnpj(dadosLogin.getLogin());
        if (loginDTO != null) {
            if (encoder.matches(dadosLogin.getSenha(), loginDTO.getSenha())) {
                String token = jwtService.getAccessToken(loginDTO.getId().toString(), loginDTO.getEmail(), loginDTO.getNome(), "empresa");
                System.out.println("TOKEN: " + token);
                return token;
            }
            throw new UsernameNotFoundException("Usuário e/ou senha incorretos");
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }

    public LoginEmpresaDTO buscarPorEmailOuNnpj(String emailOuCpnj) {
        return repository.findByEmailOrCnpjLogin(emailOuCpnj)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }


    public PerfilEmpresa carregarPerfil(UUID id) {
        return repository.carregarPerfil(id).orElseThrow();
    }


    // CRIANDO USER DETAILS DE EMPRESA
    public AuthSocket getEmpresaUserDetails(String email) {
        LoginEmpresaDTO loginDTO = buscarPorEmailOuNnpj(email);
        UserDetails userDetails = User.withUsername(loginDTO.getEmail())
                .authorities("empresa")
                .password(loginDTO.getSenha())
                .build();
        return new AuthSocket(userDetails, loginDTO.getId().toString());
    }

    public void salvarFoto(byte[] foto) {
        UUID id = UUID.fromString(SecurityContextHolder
                .getContext().getAuthentication().getCredentials().toString());
        repository.salvarFoto(foto, id);
    }

    public void salvarCapa(byte[] foto) {
        UUID id = UUID.fromString(SecurityContextHolder.getContext()
                .getAuthentication().getCredentials().toString());
        repository.salvarCapa(foto, id);
    }
}
