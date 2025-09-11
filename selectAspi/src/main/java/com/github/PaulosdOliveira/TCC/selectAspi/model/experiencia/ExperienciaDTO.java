package com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@NotBlank
@Data
public class ExperienciaDTO {
    private UUID id;
    private String empresa;
    private String cargo;
    private String descricao;
    private String duracao;


}
