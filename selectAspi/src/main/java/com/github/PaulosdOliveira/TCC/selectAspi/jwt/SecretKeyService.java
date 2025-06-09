package com.github.PaulosdOliveira.TCC.selectAspi.jwt;


import com.github.PaulosdOliveira.TCC.selectAspi.infra.repository.SecretKeyRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class SecretKeyService {

    @Autowired
    private SecretKeyRepository repository;

    private SecretKey secret;


    public SecretKey getSecret() {
        if (secret == null) {
            var chaveSalva = repository.buscarChave();
            secret = new SecretKeySpec(chaveSalva.getEncoded(), chaveSalva.getAlgoritimo());
        }
        return this.secret;
    }
}
