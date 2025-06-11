package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DadosLoginEmpresaDTO {

    @NotBlank(message = "Informe o email ou CNPJ")
    private String emailOuCpnj;

    @NotBlank(message = "Informe sua senha")
    private String senha;
}
