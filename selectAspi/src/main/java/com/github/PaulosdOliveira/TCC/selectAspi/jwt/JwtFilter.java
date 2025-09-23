package com.github.PaulosdOliveira.TCC.selectAspi.jwt;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.AuthSocket;
import com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.LoginEmpresaDTO;
import com.github.PaulosdOliveira.TCC.selectAspi.model.token.DadosToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService service;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private EmpresaService empresaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getRequestToken(request);
        if (token != null) {
            logarUsuario(token);
        }
        filterChain.doFilter(request, response);
    }

    // CORRIGIR LÓGICA
    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        var metodo = request.getMethod();
        System.out.println(path + "----------------------------------");
        System.out.println(request.getRequestURL() + "@@@@@@@@@@@@@@");
        boolean pular = (metodo.equals(HttpMethod.POST.name()) && path.contains("/login") || path.contains("/vaga/buscar"))
                        || (!metodo.equals(HttpMethod.PUT.name()) && path.contains("/foto") || path.contains("/capa"))
                        || (metodo.equals(HttpMethod.GET.name()) && path.contains("/vaga/buscar") || path.contains("/vaga?idEmpresa="))
                        || path.contains("conect") || (path.equals("/candidato") && metodo.equals(HttpMethod.POST.name()));
        System.out.println(pular);
        return pular;
    }


    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("TOKEN: " + token);
        if (token != null) {
            String[] tokenDividido = token.split(" ");
            return tokenDividido[1];
        }
        return null;
    }

    // LOGANDO USUÁRIO ATRAVÉZ DO TOKEN
    public void logarUsuario(String token) {
        if (token != null) {
            DadosToken dadosToken = service.getDadosToken(token);
            AuthSocket authSocket;
            if (dadosToken.getPerfil().equals("candidato")) {
                System.out.println("Candidato ################");
                authSocket = candidatoService.getCandidatoUserDetails(dadosToken.getEmail());
            } else {
                System.out.println("Empresa ###################################");
                System.out.println(dadosToken.getEmail() + " " + dadosToken.getPerfil());
                authSocket = empresaService.getEmpresaUserDetails(dadosToken.getEmail());
            }
            // LOGANDO USUÁRIO
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(authSocket.getUserDetails(), authSocket.getIdUsuario(), authSocket.getUserDetails().getAuthorities()));
        }
    }


}
