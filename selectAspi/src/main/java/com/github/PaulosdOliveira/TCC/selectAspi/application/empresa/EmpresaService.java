package com.github.PaulosdOliveira.TCC.selectAspi.application.empresa;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.EmpresaRepository;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.DadosLoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.CadastroEmpresaDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.validation.EmpresaValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.Empresa;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import com.github.PaulosdOliveira.TCC.selectAspi.jwt.JwtService;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpresaService {

    private final EmpresaRepository repository;
    private final PasswordEncoder encoder;
    private final EmpresaValidator validator;
    private final JwtService jwtService;


    public void cadastrarEmpresa(CadastroEmpresaDTO dadosCadastrais) {
        validator.validar(dadosCadastrais.getEmail(), dadosCadastrais.getCnpj());
        dadosCadastrais.setSenha(encoder.encode(dadosCadastrais.getSenha()));
        var empresa = new Empresa(dadosCadastrais);
        repository.save(empresa);
    }


    public String getAccessToken(DadosLoginEmpresaDTO dadosLogin) {
        LoginEmpresaDTO loginDTO = buscarPorEmailOuNnpj(dadosLogin.getEmailOuCpnj());
        if (encoder.matches(dadosLogin.getSenha(), loginDTO.getSenha())) {
            return jwtService.getAccessToken(loginDTO.getId(), loginDTO.getEmail(), loginDTO.getNome(), "empresa");
        }
        throw new UsernameNotFoundException("Usário e/ou senha incorretos");
    }

    public LoginEmpresaDTO buscarPorEmailOuNnpj(String emailOuCpnj) {
        return repository.findByEmailOrCnpjLogin(emailOuCpnj)
                .orElseThrow(() -> new UsernameNotFoundException("Usário não encontrado"));
    }

    public void logarEmpresa(String email) {
        LoginEmpresaDTO loginDTO = buscarPorEmailOuNnpj(email);
        UserDetails userDetails = User.withUsername(loginDTO.getEmail())
                .authorities("empresa")
                .password(loginDTO.getSenha())
                .build();
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, loginDTO.getId(), userDetails.getAuthorities()));
    }

}
