package com.github.PaulosdOliveira.TCC.selectAspi.model.experiencia;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CadastroExperienciaDTO {

    @NotBlank(message = "Campo obrigatório")
    private String empresa;

    @NotBlank(message = "Campo obrigatório")
    private String cargo;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotBlank(message = "Campo obrigatório")
    private LocalDate inicio;

    @NotBlank(message = "Campo obrigatório")
    private LocalDate fim;
}
