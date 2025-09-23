package com.github.PaulosdOliveira.TCC.selectAspi.jwt;

import com.github.PaulosdOliveira.TCC.selectAspi.model.token.DadosToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import io.jsonwebtoken.Jwts;

import java.util.HashMap;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private SecretKeyService secretKeyService;

    public String getAccessToken(String id, String email, String nome, String perfil) {
        return Jwts.builder()
                .subject(email)
                .signWith(secretKeyService.getSecret())
                .claims(getClaims(id, nome, perfil))
                .expiration(getExpiration())
                .compact();
    }

    private Date getExpiration() {
        Instant expiration = LocalDateTime.now().plusDays(5).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(expiration);
    }

    private Map<String, Object> getClaims(String id, String nome, String perfil) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("nome", nome);
        map.put("perfil", perfil);
        return map;
    }


    // RECUPERANDO DADOS DO TOKEN
    public DadosToken getDadosToken(String token) {
        var payLoad = Jwts.parser()
                .verifyWith(secretKeyService.getSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return new DadosToken(payLoad.getSubject(), payLoad.get("perfil").toString());
    }
}
