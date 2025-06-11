package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;


import lombok.Data;

@Data
public class LoginCandidatoDTO {

    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;
}
