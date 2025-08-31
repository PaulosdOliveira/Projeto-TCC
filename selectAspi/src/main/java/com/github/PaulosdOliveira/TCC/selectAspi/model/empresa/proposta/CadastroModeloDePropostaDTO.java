package com.github.PaulosdOliveira.TCC.selectAspi.model.empresa.proposta;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;


@Data
public class CadastroModeloDePropostaDTO {
    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;
}
