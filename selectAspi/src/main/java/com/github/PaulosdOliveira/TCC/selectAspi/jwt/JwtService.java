package com.github.PaulosdOliveira.TCC.selectAspi.jwt;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.LoginCandidatoDTO;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {


    @Autowired
    private SecretKeyService secretKeyService;

    public String getAccessToken(LoginCandidatoDTO candidatoEnontrado) {
        return Jwts.builder()
                .subject(candidatoEnontrado.getEmail())
                .signWith(secretKeyService.getSecret())
                .claims(getClaims(candidatoEnontrado.getId(), candidatoEnontrado.getNome(),
                        candidatoEnontrado.getPerfil()))
                .expiration(getExpiration())
                .compact();
    }

    private Date getExpiration() {
        Instant expiration = LocalDateTime.now().plusDays(5).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(expiration);
    }

    private Map<String, Object> getClaims(Long id, String nome, String perfil) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("nome", nome);
        map.put("perfil", perfil);
        return map;
    }
}
