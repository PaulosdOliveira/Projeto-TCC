package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QualificacaoUsuarioDTO {
    @NotNull(message = "Algo de errado ocorreu, informe o seu usuário")
    private Long idCandidato;

    @NotNull(message = "Algo de errado ocorreu, informa a qualificação")
    private Long idQualificacao;

    @NotNull(message = "Informe o nivel de hábilidade")
    private Nivel nivel;
}
