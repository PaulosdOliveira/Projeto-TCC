package com.github.PaulosdOliveira.TCC.selectAspi.model.candidato;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DadosLoginCandidatoDTO {

    @NotBlank(message = "Informe seu cpf ou email de acesso")
    private String cpfOuEmail;

    @NotBlank(message = "Informe a sua senha")
    private String senha;
}
