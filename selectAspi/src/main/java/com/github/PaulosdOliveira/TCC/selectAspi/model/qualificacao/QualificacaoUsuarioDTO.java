package com.github.PaulosdOliveira.TCC.selectAspi.model.qualificacao;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QualificacaoUsuarioDTO {

    @NotNull(message = "Algo de errado ocorreu")
    private Long idQualificacao;

    @NotNull(message = "Informe o nivel de hábilidade")
    private Nivel nivel;


}
