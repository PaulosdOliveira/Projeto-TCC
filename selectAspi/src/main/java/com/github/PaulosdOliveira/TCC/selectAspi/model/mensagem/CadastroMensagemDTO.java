package com.github.PaulosdOliveira.TCC.selectAspi.model.mensagem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class CadastroMensagemDTO {
    @NotBlank(message = "A mensagem não pode ser vázia")
    private final String texto;

    @NotNull(message = "Algo de errado")
    private final UUID idEmpresa;

    @NotNull(message = "Algo de errado")
    private final Long idCandidato;

    private final String perfilRemetente;
}
