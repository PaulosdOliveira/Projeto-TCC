package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginEmpresaDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private static String perfil = "empresa";
}
