package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class MensagemDTO {
    @NotBlank(message = "A mensagem não pode ser vázia")
    private String texto;

    @NotNull(message = "Algo de errado")
    private UUID idEmpresa;

    @NotNull(message = "Algo de errado")
    private Long idCandidato;
}
