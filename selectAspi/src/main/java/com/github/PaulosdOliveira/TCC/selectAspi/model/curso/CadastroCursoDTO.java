package com.github.PaulosdOliveira.TCC.selectAspi.model.curso;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroCursoDTO {

    @NotBlank(message = "Campo obrigatório")
    private String instituicao;

    @NotBlank(message = "Campo obrigatório")
    private String curso;

    @NotNull(message = "Campo obrigatório")
    private short cargaHoraria;
}
