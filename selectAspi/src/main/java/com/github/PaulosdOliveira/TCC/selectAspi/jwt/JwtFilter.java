package com.github.PaulosdOliveira.TCC.selectAspi.jwt;

import com.github.PaulosdOliveira.TCC.selectAspi.application.candidato.CandidatoService;
import com.github.PaulosdOliveira.TCC.selectAspi.application.empresa.EmpresaService;
import com.github.PaulosdOliveira.TCC.selectAspi.model.token.DadosToken;
import org.springframework.beans.factory.annotation.Autowired;
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
                candidatoService.logarCandidato(dadosToken.getEmail());
            } else {
                empresaService.logarEmpresa(dadosToken.getEmail());
            }
        }
        filterChain.doFilter(request, response);
    }

    // CORRIGIR LÓGICA
    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/login") || path.contains("/empresa");
    }


    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String[] tokenDividido = token.split(" ");
            return tokenDividido[1];
        }
        return null;
    }
}
