package com.github.PaulosdOliveira.TCC.selectAspi.model.formacao;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CadastroFormacaoDTO {

    @NotBlank(message = "Campo obrigatório")
    private String instituicao;

    @NotBlank(message = "Campo obrigatório")
    private String curso;

    @NotNull(message = "Campo obrigatório")
    private Nivel nivel;

    @NotNull(message = "Campo obrigatório")
    private Situacao situacao;


}
