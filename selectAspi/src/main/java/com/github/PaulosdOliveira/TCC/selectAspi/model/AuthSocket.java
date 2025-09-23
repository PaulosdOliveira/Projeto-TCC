package com.github.PaulosdOliveira.TCC.selectAspi.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AuthSocket {
    private final UserDetails userDetails;
    private final String idUsuario;
}
