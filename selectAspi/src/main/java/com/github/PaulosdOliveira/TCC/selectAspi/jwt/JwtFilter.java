package com.github.PaulosdOliveira.TCC.selectAspi.jwt;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.token.DadosToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
            DadosToken dadosToken = service.getEmailByToken(token);
            if (dadosToken.getPerfil().equals("candidato")) {
                System.out.println("Candidato ################");
                candidatoService.logarCandidato(dadosToken.getEmail());
            } else {
                System.out.println("Empresa ###################################");
                System.out.println(dadosToken.getEmail() + " " + dadosToken.getPerfil());
                empresaService.logarEmpresa(dadosToken.getEmail());
            }
        }
        filterChain.doFilter(request, response);
    }

    // CORRIGIR LÓGICA
    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println(path + "----------------------------------");
        System.out.println(request.getRequestURL() + "@@@@@@@@@@@@@@");

        System.out.println(path.contains("/login") || path.contains("/cadastro") || path.contains("/qualificacao"));

        return path.contains("/login") || path.contains("/cidades") || path.contains("/estados") || path.contains("/cadastro")
               || path.contains("/qualificacao")   && !path.contains("/qualificacao-candidato");
    }


    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println(token + "@@@@@@@@");
        if (token != null) {
            String[] tokenDividido = token.split(" ");
            return tokenDividido[1];
        }
        return null;
    }
}
