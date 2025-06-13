package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ConsultaQualificacaoUsuario {
    @NotNull(message = "Aqu")
    private final Long idQualificacao;

    @NotNull(message = "Oi")
    private final Nivel nivel;
}
