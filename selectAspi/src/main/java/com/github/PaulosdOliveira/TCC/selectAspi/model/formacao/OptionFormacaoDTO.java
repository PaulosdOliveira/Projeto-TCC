package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;

import com.github.PaulosdOliveira.TCC.selectAspi.model.candidato.Candidato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

// USADO NO AUTO-COMPLETE DO FRONT END
@Data
public class OptionFormacaoDTO {
    private final UUID id;
    private final String curso;
}
